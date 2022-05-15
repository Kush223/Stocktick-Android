package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot1Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity

private val retirementType = listOf(
    "LIKE A KING",
    "I AM HAPPY WITH THE WAY I AM",
    "LIKE A MONK"
)

private val savingLocation = listOf(
    "SAFE (PF, FD, ETC)",
    "AGGRESSIVE (MUTUAL FUNDS, EQUITY, ETC)"
)

class Page6dot1 : Fragment(R.layout.fragment_page6dot1), AdapterView.OnItemSelectedListener  {
    private lateinit var binding: FragmentPage6dot1Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6dot1Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(6)
        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot1_to_page6dot2)
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot1_to_page6dot2)
        }


        val type = binding.spType
        type.onItemSelectedListener = this
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_spinner_dropdown, retirementType)
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        type.adapter = adapter

        val saving = binding.spSaving
        saving.onItemSelectedListener = this
        val adapter2 = ArrayAdapter(requireContext(), R.layout.gender_spinner_dropdown, savingLocation)
        adapter2.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        saving.adapter = adapter2

    }



    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}