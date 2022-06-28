package com.example.stocktick.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEditProfileBinding
import com.example.stocktick.models.requests.UpdateUserProfileDTO
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import com.example.stocktick.utility.extension_functions.copyExif
import com.example.stocktick.utility.extension_functions.reduceImageSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


private const val TAG = "EditProfileTAG"
class EditProfile : Fragment(R.layout.fragment_edit_profile),
    View.OnClickListener,
    AdapterView.OnItemSelectedListener {

    private var profileUrl : String = ""


    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var gender: Spinner
    private lateinit var tokenSharedPreference: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

        binding = FragmentEditProfileBinding.bind(view)
        binding.submitBtn.setOnClickListener(this)
        binding.profileImage.setOnClickListener(this)
        binding.imgEditBtn.setOnClickListener(this)


        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()


        gender = binding.gender

        gender.onItemSelectedListener = this
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.debit_credit_spinner,
            com.example.stocktick.ui.mutual_funds.risk_factor.fragments.genders
        )
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        gender.adapter = adapter
        autofill()



    }

    private fun autofill(){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.getUserDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        binding.etName.setText(body.name ?: "")
                        binding.etEmail.setText(body.email ?: "")
                        binding.etAge.setText(body.age.toString())
                        when (body.gender) {
                            "Male" -> gender.setSelection(0)
                            "Female" -> gender.setSelection(1)
                            "Others" -> gender.setSelection(2)
                        }
                        profileUrl = body.profile_url ?: ""
                        Glide.with(requireActivity()).load(profileUrl).into(binding.profileImage)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Failed to retrieve info. \nPlease check your internet connection",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Failed to retrieve info. \nPlease check your internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
        }

        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
            Log.d(TAG, "Result :${intent?.data}")
            val uri = intent?.data ?: return@registerForActivityResult


            uploadFile(
                uri
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitBtn -> {
                onSubmit {
                    if (it){
                        Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).updateDrawerProfile()
                        navController.popBackStack(R.id.navigation_home, false)
                    }
                    else {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            R.id.profileImage -> {
                openFile()
            }
            R.id.imgEditBtn ->{
                openFile()
            }
        }
    }

    private fun uploadFile(
        uri: Uri)
    {
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val docFile = DocumentFile.fromSingleUri(requireContext(), uri) ?: return@launch
                val suffix = when (docFile.type){
                    "image/jpeg"->".jpg"
                    "image/pjeg" -> ".jpg"
                    "image/png" -> ".png"
                    else ->".jpg"
                }
                Log.d(TAG, "uploadFile: format from docFile :${docFile.type}")
                var inputStream = requireContext().contentResolver.openInputStream(uri) ?: return@launch
                val tempFile = File.createTempFile("toUpload", suffix, requireActivity().getExternalFilesDir(null))
                //reduces image size
                inputStream.reduceImageSize(tempFile)
                inputStream.close()
                val tempFileOriginal = File.createTempFile("toUpload", suffix, requireActivity().getExternalFilesDir(null))

                inputStream = requireContext().contentResolver.openInputStream(uri) ?: return@launch
                FileOutputStream(tempFileOriginal, false).use { outputStream->
                    var read: Int
                    val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
                    while (inputStream.read(bytes).also { read = it } != -1) {
                        outputStream.write(bytes, 0, read)
                    }
                }
                copyExif(tempFileOriginal.absolutePath,tempFile.absolutePath)





                val requestBody = tempFile
                    .asRequestBody(requireContext().contentResolver.getType(uri)?.toMediaTypeOrNull())

                val body = MultipartBody.Part.createFormData("file",docFile.name, requestBody)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Uploading... Please wait.", Toast.LENGTH_SHORT).show()
                }
                val response =
                    RetrofitClientInstance.retrofitService.uploadFile(
                       authToken = tokenSharedPreference,
                        file = body
                    )

                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Profile photo uploaded", Toast.LENGTH_SHORT).show()
                    if (response.body() != null && response.body()!!.file != null)
                    profileUrl = response.body()!!.file!!

                    Glide.with(requireActivity()).load(profileUrl).into(binding.profileImage)
                    Log.d(TAG, "onSubmit: ${response.body()}")
                }
                tempFile.delete()
                tempFileOriginal.delete()
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to upload file", Toast.LENGTH_SHORT).show()

                }
                Log.d(TAG, error.toString())
            }
        }
        }

    private fun onSubmit(
        onResponse: (
            isSuccessful: Boolean
                ) -> Unit
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.updateUserInfo(
                        authToken = tokenSharedPreference,
                        UpdateUserProfileDTO(
                            name = binding.etName.text.toString(),
                            age = binding.etAge.text.toString().toInt(),
                            email = binding.etEmail.text.toString(),
                            gender = gender.selectedItem.toString(),
                            profile_url = profileUrl
                        )
                    )
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onSubmit: ${response.body()}")
                    onResponse(
                        response.isSuccessful
                    )
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    onResponse(
                        false
                    )
                }
                Log.d("ERROR", error.toString())
            }
        }
    }


}