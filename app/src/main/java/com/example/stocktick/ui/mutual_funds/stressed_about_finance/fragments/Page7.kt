package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage7Binding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page7Dto
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


private const val TAG = "Page7"
class Page7 : Fragment(R.layout.fragment_page7) {
    private val viewModel: MainViewModel by  activityViewModels()
    private lateinit var binding: FragmentPage7Binding
    private lateinit var imageView: ImageView
    private var uri: Uri? = null
    private var imageUrl: String? = null
    private lateinit var tokenSharedPreference: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage7Binding.bind(view)
        imageView = binding.uploadImageView
        val tracker = (activity as HostActivity).customTracker
        tracker.move(10)
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()

        imageView.setOnClickListener{
            openFile()
        }
        binding.btNext.setOnClickListener{
            if (uri == null) {
                Toast.makeText(requireContext(), "Please pick a family photo", Toast.LENGTH_SHORT).show()

            } else {
                uploadFile(uri!!){
                    viewModel.postPage7(
                        page7Dto = Page7Dto(
                            img = imageUrl ?: ""
                        )
                    ){
                        Toast.makeText(requireContext(), "Success Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
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
            uri = intent?.data ?: return@registerForActivityResult
            Glide.with(requireActivity()).load(uri).into(imageView)

        }
    }

    private fun uploadFile(
        uri: Uri,
        onResponse: (isSuccessful: Boolean)-> Unit
    )
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
                    .asRequestBody(requireContext().contentResolver.getType(uri)?.let { it.toMediaTypeOrNull() })

                val body = MultipartBody.Part.createFormData("file",docFile.name, requestBody);
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Uploading... Please wait.", Toast.LENGTH_SHORT).show()
                }
                val response =
                    RetrofitClientInstance.retrofitService.uploadFile(
                        authToken = tokenSharedPreference,
                        file = body
                    )

                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Photo uploaded", Toast.LENGTH_SHORT).show()
                    if (response.body() != null && response.body()!!.file != null)
                        imageUrl = response.body()!!.file!!

                    onResponse(true)

                }
                tempFile.delete()
                tempFileOriginal.delete()
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to upload file", Toast.LENGTH_SHORT).show()
                    onResponse(false)
                }
                Log.d(TAG, error.toString())
            }
        }
    }




}