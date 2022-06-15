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
import com.example.stocktick.databinding.FragmentKeyPeopleEditBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.models.KeyPeopleModel
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KeyPeopleEditFragment : Fragment(R.layout.fragment_key_people_edit) {

    private lateinit var binding: FragmentKeyPeopleEditBinding
    private lateinit var tokenSharedPreference: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A)
                .toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentKeyPeopleEditBinding.bind(view)
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
    }

    private fun autofill() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.getKeyPeople(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        val data = body[0]
                        binding.tvDoctorName.setText(data.familyDoctor)
                        binding.tvDoctorContactNo.setText(data.familyDoctorC1)
                        binding.tvDoctorAlternateContactNo.setText(data.familyDoctorC2)
                        binding.tvCharName.setText(data.CA)
                        binding.tvCharContactNo.setText(data.CAC1)
                        binding.tvCharAlternateContactNo.setText(data.CAC2)
                        binding.tvAdvocateName.setText(data.advisor)
                        binding.tvAdvocateContactNo.setText(data.advisorC1)
                        binding.tvAdvocateAlternateContactNo.setText(data.advisorC2)
                        binding.tvFinName.setText(data.advisor)
                        binding.tvFinContactNo.setText(data.advisorC1)
                        binding.tvFinAlternateContactNo.setText(data.advisorC2)
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

    private fun onSubmit(result: (successful: Boolean) -> Unit) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val keyPeople = KeyPeopleModel(
                    binding.tvDoctorName.text.toString(),
                    binding.tvDoctorContactNo.text.toString(),
                    binding.tvDoctorAlternateContactNo.text.toString(),
                    binding.tvCharName.text.toString(),
                    binding.tvCharContactNo.text.toString(),
                    binding.tvCharAlternateContactNo.text.toString(),
                    binding.tvAdvocateName.text.toString(),
                    binding.tvAdvocateContactNo.text.toString(),
                    binding.tvAdvocateAlternateContactNo.text.toString(),
                    binding.tvFinName.text.toString(),
                    binding.tvFinContactNo.text.toString(),
                    binding.tvFinAlternateContactNo.text.toString()

                    )
                val response = RetrofitClientInstance.retrofitService.postKeyPeople(
                    authToken = tokenSharedPreference,
                    body = keyPeople
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        result(true)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        result(false)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    result(false)
                }
            }
        }
    }


}