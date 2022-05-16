package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.ChildMarriageCalculatorFragmentLayoutBinding
import com.example.stocktick.databinding.EmiCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow

class ChildMarriageCalculatorFragment: Fragment(R.layout.child_marriage_calculator_fragment_layout) {
    private lateinit var binding: ChildMarriageCalculatorFragmentLayoutBinding

    private lateinit var etCurrentCostOfMarriage: NeumorphEditText
    private lateinit var etInflation: NeumorphEditText
    private lateinit var etCurrentAge: NeumorphEditText
    private lateinit var etMarriageAge: NeumorphEditText
    private lateinit var etCurrentInvestment: NeumorphEditText
    private lateinit var etExpectedReturns: NeumorphEditText

    // results parameters
    private var emi  = 0.0

    //Output widgets
    private lateinit var tvFutureCost: TextView
    private lateinit var tvAppreciation: TextView
    private lateinit var tvDeficitCorpus: TextView
    private lateinit var tvLumpsum: TextView
    private lateinit var tvMonthlyInvestmentRequired: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChildMarriageCalculatorFragmentLayoutBinding.bind(view)

        //view initializations
        etCurrentCostOfMarriage = binding.etAmount
        etInflation = binding.etTimePeriod
        etCurrentAge = binding.etCurrentSaving
        etMarriageAge = binding.etMonthlySaving
        etCurrentInvestment = binding.etInvestAmount
        etExpectedReturns = binding.etExpectedReturn


        //output view initialization
        tvFutureCost = binding.futureCostOfMarriage
        tvAppreciation = binding.appreciation
        tvDeficitCorpus = binding.deficitCorpus
        tvLumpsum = binding.lumpsum
        tvMonthlyInvestmentRequired = binding.monthlyEmi

        etCurrentInvestment.listen()
        etInflation.listen()
        etCurrentAge.listen()
        etMarriageAge.listen()
        etCurrentInvestment.listen()
        etExpectedReturns.listen()

    }

    //listener extension function
    private fun NeumorphEditText.listen(){
        this.onTextChangeListener{
            calculate()
        }
    }


    private fun calculate(){
        try {
            //variables
            val pv = etCurrentCostOfMarriage.getText().toDouble()
            val ir = etInflation.getText().toDouble()
            val n = etMarriageAge.getText().toDouble() - etCurrentAge.getText().toDouble()

            val fv = pv * (1+ir/100).pow(n)


        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}