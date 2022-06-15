package com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.personal_details

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPersonalDetailsEditBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.models.PersonalDetailsModel
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonalDetailsEditFragment : Fragment(R.layout.fragment_personal_details_edit) {

  private lateinit var binding : FragmentPersonalDetailsEditBinding
    private lateinit var tokenSharedPreference: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonalDetailsEditBinding.bind(view)
        autofill()
        binding.fabDone.setOnClickListener{
            onSubmit { 
                if (it){
                    view.findNavController().navigateUp()

                }
                else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }


        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()
        
    }

    private fun autofill(){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.getPersonalDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        val data = body[0]
                        binding.tvName.setText(data.name)
                        binding.tvLocation.setText(data.address)
                        binding.tvMobileNo.setText(data.mobile)
                        binding.tvEmailId.setText(data.emailId)
                        binding.tvDOB.setText(data.dob)
                        binding.tvBloodGroup.setText(data.bloodGroup)
                        binding.tvEmName.setText(data.emergencyName)
                        binding.tvEmAddress.setText(data.emergencyAddress)
                        binding.tvEmTelephone.setText(data.emergencyContact)
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
    
    private fun onSubmit(result : (successful : Boolean )-> Unit){
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val personalDetails = PersonalDetailsModel(
                            binding.tvName.text.toString(),
                            binding.tvLocation.text.toString(),
                            binding.tvMobileNo.text.toString(),
                            binding.tvEmailId.text.toString(),
                            binding.tvDOB.text.toString(),
                            binding.tvBloodGroup.text.toString(),
                            binding.tvEmName.text.toString(),
                            binding.tvEmAddress.text.toString(),
                            binding.tvEmTelephone.text.toString(),
                )
                val response = RetrofitClientInstance.retrofitService.postPersonalDetails(
                    authToken = tokenSharedPreference,
                    body = personalDetails
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main){
                        result(true)
                    }
                } else {
                    withContext(Dispatchers.Main){
                        result(false)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    result(false)
                }
            }
        }
    }
}