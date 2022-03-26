package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentScreen1Binding


class Page1 :
    Fragment(R.layout.fragment_screen1),
    AdapterView.OnItemSelectedListener {

    private val maritalStatus = listOf(
        "Married",
        "Single" //so sad
    )
    private val children = listOf(
        "NA",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six"
    )
    private val parents = listOf(
        "Both Alive",
        "One Alive",
        "None Alive"
    )


    private lateinit var binding: FragmentScreen1Binding
    private lateinit var maritalStatusSpinner: Spinner
    private lateinit var childrenSpinner: Spinner
    private lateinit var parentsSpinner: Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScreen1Binding.bind(view)
        binding.btNext.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_page1_to_page2)
        }
        maritalStatusSpinner = binding.spStatus
        childrenSpinner = binding.spChildren
        parentsSpinner = binding.spParents

        maritalStatusSpinner.onItemSelectedListener = this
        childrenSpinner.onItemSelectedListener = this
        parentsSpinner.onItemSelectedListener = this

        val adapterMaritalStatus = ArrayAdapter(
            requireContext(),
            R.layout.gender_spinner_dropdown,
            maritalStatus

        )
        adapterMaritalStatus.setDropDownViewResource(R.layout.gender_spinner_dropdown)

        val adapterChildren = ArrayAdapter(
            requireContext(),
            R.layout.gender_spinner_dropdown,
            children
            )
        adapterChildren.setDropDownViewResource(R.layout.gender_spinner_dropdown)

        val adapterParents = ArrayAdapter(
            requireContext(),
            R.layout.gender_spinner_dropdown,
            parents
            )
        adapterChildren.setDropDownViewResource(R.layout.gender_spinner_dropdown)

        maritalStatusSpinner.adapter = adapterMaritalStatus
        childrenSpinner.adapter = adapterChildren
        parentsSpinner.adapter = adapterParents


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}