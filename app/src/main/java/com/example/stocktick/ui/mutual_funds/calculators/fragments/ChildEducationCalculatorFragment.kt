package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.ChildEducationCalculatorFragmentLayoutBinding

class ChildEducationCalculatorFragment : Fragment(R.layout.child_education_calculator_fragment_layout){

    private lateinit var binding: ChildEducationCalculatorFragmentLayoutBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChildEducationCalculatorFragmentLayoutBinding.bind(view)
    }
}