package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentScreen1Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page1Dto


class Page1 :
    Fragment(R.layout.fragment_screen1),
    AdapterView.OnItemSelectedListener {

    private val viewModel: MainViewModel by activityViewModels()

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
        val tracker = (activity as HostActivity).customTracker
        tracker.move(0)
        tracker.visibility = View.VISIBLE

        binding.btNext.setOnClickListener {
            handleOnClick()
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

    private fun handleOnClick() {
        val children = when (childrenSpinner.selectedItem.toString()){
            "NA"->0
            "One"->1
            "Two"->2
            "Three"->3
            "Four"->4
            "Five"->5
            "Six"->6
            else ->0
        }
        viewModel.postPage1(
                page1Dto = Page1Dto(
                        children = children,
                        parents = parentsSpinner.selectedItem.toString(),
                        status = maritalStatusSpinner.selectedItem.toString()
                )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page1_to_page2)
            } else {
                view?.findNavController()?.navigate(R.id.action_page1_to_page2) //remove it later
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
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