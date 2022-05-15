package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentWelcomeBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page1Dto

val genders = listOf(
    "Mr.",
    "Mrs."
)

class WelcomeFragment : Fragment(R.layout.fragment_welcome), AdapterView.OnItemSelectedListener {


    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var gender: Spinner

    private lateinit var etName: EditText
    private lateinit var etAge: EditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.visibility = View.GONE


        gender = binding.gender
        etName = binding.etUserName
        etAge = binding.age

        gender.onItemSelectedListener= this
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_spinner_dropdown, genders)
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        gender.adapter = adapter

        binding.btGetStarted.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_page1)
        }

        autofill()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun autofill(){
        viewModel.getUserDetail{ isSuccessful, userDetail ->
            if (isSuccessful && userDetail!=null ){
                when (userDetail.gender){
                    "Male"-> gender.setSelection(0)
                    "Female"-> gender.setSelection(1)
                }
                etName.setText(userDetail.name)
                etAge.setText(userDetail.age.toString())



            }


        }
    }




}