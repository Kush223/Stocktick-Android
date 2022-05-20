package com.example.stocktick.ui.profile

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentViewProfileBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import soup.neumorphism.NeumorphFloatingActionButton


class ViewProfile : Fragment(R.layout.fragment_view_profile) {
    private lateinit var binding: FragmentViewProfileBinding

    private lateinit var fabEdit: NeumorphFloatingActionButton
    private val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentViewProfileBinding.bind(view)
        fabEdit = binding.fabEdit

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        val tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()

        fabEdit.setOnClickListener{
            navController.navigate(R.id.action_viewProfile_to_editProfile)
        }

        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

        lifecycleScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getUserDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        binding.tvName.text = body.name
                        binding.tvEmail.text = body.email
                        binding.tvPhoneNo.text = body.phone
                        binding.tvGender.text = body.gender
                        binding.tvAge.text = "${body.age.toString()} yrs"
                        Glide.with(requireActivity()).load(body.profile_url).into(binding.profileImage)
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireContext(), "Failed to retrieve info. \nPlease check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to retrieve info. \nPlease check your internet connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}