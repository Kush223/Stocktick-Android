package com.example.stocktick.ui.profile

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEditProfileBinding
import com.example.stocktick.models.requests.UpdateUserProfileDTO
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


val genders = listOf(
    "Male",
    "Female",
    "Others"
)

class EditProfile : Fragment(R.layout.fragment_edit_profile),
    View.OnClickListener,
    AdapterView.OnItemSelectedListener {


    private val navController by lazy {
        findNavController()
    }

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var gender: Spinner
    private lateinit var tokenSharedPreference: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditProfileBinding.bind(view)
        binding.submitBtn.setOnClickListener(this)


        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()


        gender = binding.gender

        gender.onItemSelectedListener = this
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.gender_spinner_dropdown,
            com.example.stocktick.ui.mutual_funds.risk_factor.fragments.genders
        )
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        gender.adapter = adapter


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
                        binding.etPhoneNo.setText(body.phone ?: "")
                        binding.etAge.setText(body.age.toString())
                        when (body.gender) {
                            "Male" -> gender.setSelection(0)
                            "Female" -> gender.setSelection(1)
                            "Others" -> gender.setSelection(2)
                        }
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitBtn -> {
                onSubmit {
                    if (it){
                        navController.popBackStack(R.id.navigation_home, false)
                        Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
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
                            name = binding.etName.getText(),
                            age = binding.etAge.getText().toInt(),
                            email = binding.etEmail.getText(),
                            gender = gender.selectedItem.toString(),
                            profile_url = ""
                        )
                    )
                withContext(Dispatchers.Main) {
                    Log.d("asdfasdf", "onSubmit: ${response.body()}")
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