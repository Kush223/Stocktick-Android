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
import com.example.stocktick.databinding.FragmentPersonalDetailsBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import soup.neumorphism.NeumorphFloatingActionButton

class PersonalDetailsFragment : Fragment(R.layout.fragment_personal_details) {

    private lateinit var binding: FragmentPersonalDetailsBinding
    private lateinit var fabEdit: NeumorphFloatingActionButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPersonalDetailsBinding.bind(view)

        fabEdit = binding.fabEdit

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        val tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()

        fabEdit.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_personalDetailsFragment_to_personalDetailsEditFragment)
        }


        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.getPersonalDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        val data = body[0]
                        binding.tvName.text = data.name
                        binding.tvLocation.text = data.address
                        binding.tvMobileNo.text = data.mobile
                        binding.tvEmailId.text = data.emailId
                        binding.tvDOB.text = data.dob
                        binding.tvBloodGroup.text = data.bloodGroup
                        binding.tvEmName.text = data.emergencyName
                        binding.tvEmAddress.text = data.emergencyAddress
                        binding.tvEmTelephone.text = data.emergencyContact
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
}