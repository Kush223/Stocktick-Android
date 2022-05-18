package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.ChildEducationCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow

class ChildEducationCalculatorFragment : Fragment(R.layout.child_education_calculator_fragment_layout){

    private lateinit var binding: ChildEducationCalculatorFragmentLayoutBinding

    //input fields
    private lateinit var etCurrentCostOfEducation: NeumorphEditText
    private lateinit var etInflation: NeumorphEditText
    private lateinit var etCurrentAge: NeumorphEditText
    private lateinit var etCorpusAge: NeumorphEditText
    // private lateinit var etCurrentInvestment: NeumorphEditText
    private lateinit var etExpectedReturns: NeumorphEditText

    //output fields
    private lateinit var tvFutureCost: TextView
    private lateinit var tvMonthlyInstallment: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ChildEducationCalculatorFragmentLayoutBinding.bind(view)

        //view initializations
        etCurrentCostOfEducation = binding.etAmount
        etInflation = binding.etTimePeriod
        etCurrentAge = binding.etCurrentSaving
        etCorpusAge = binding.etMonthlySaving
        etExpectedReturns = binding.etExpectedReturn

        tvFutureCost = binding.futureCostOfMarriage
        tvMonthlyInstallment = binding.monthlyEmi


        etInflation.listen()
        etCurrentAge.listen()
        etCorpusAge.listen()
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
            val pv = etCurrentCostOfEducation.getText().toDouble()
            val i = etInflation.getText().toDouble()
            val n = etCorpusAge.getText().toDouble() - etCurrentAge.getText().toDouble()
            var r = etExpectedReturns.getText().toDouble()

            //calculations
            val fv = pv * (1+i/100).pow(n)
            r /= 1200
            val mi = fv / ((((1+r).pow(n*12)-1)/r)*(r+1))

            //writing to output
            val sFv = "₹${String.format("%.2f",fv)}"
            val sMi = "₹${String.format("%.2f",mi)}"

            tvFutureCost.text = sFv
            tvMonthlyInstallment.text = sMi

        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}