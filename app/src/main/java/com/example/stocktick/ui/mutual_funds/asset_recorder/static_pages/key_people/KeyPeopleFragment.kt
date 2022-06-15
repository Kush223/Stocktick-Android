package com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.key_people

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentKeyPeopleBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import soup.neumorphism.NeumorphFloatingActionButton

class KeyPeopleFragment : Fragment(R.layout.fragment_key_people) {

    private lateinit var binding : FragmentKeyPeopleBinding
    private lateinit var fabEdit: NeumorphFloatingActionButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentKeyPeopleBinding.bind(view)
        fabEdit = binding.fabEdit

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        val tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()

        fabEdit.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_keyPeopleFragment_to_keyPeopleEditFragment)
        }
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.getKeyPeople(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        val data = body[0]
                        binding.tvDoctorName.text = data.familyDoctor
                        binding.tvDoctorContactNo.text = data.familyDoctorC1
                        binding.tvDoctorAlternateContactNo.text = data.familyDoctorC2
                        binding.tvCharName.text = data.CA
                        binding.tvCharContactNo.text = data.CAC1
                        binding.tvCharAlternateContactNo.text = data.CAC2
                        binding.tvAdvocateName.text = data.advocate
                        binding.tvAdvocateContactNo.text = data.advocateC1
                        binding.tvAdvocateAlternateContactNo.text = data.advocateC2
                        binding.tvFinName.text = data.advisor
                        binding.tvFinContactNo.text = data.advisorC1
                        binding.tvFinAlternateContactNo.text = data.advisorC2
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