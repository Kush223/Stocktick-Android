package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentWelcomeBinding

val genders = listOf(
    "Mr.",
    "Mrs."
)

class WelcomeFragment : Fragment(R.layout.fragment_welcome), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var gender: Spinner


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        gender = binding.gender

        gender.onItemSelectedListener= this
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_spinner_dropdown, genders)
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        gender.adapter = adapter

        binding.btGetStarted.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_welcomeFragment_to_page1)
        }
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
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }




}