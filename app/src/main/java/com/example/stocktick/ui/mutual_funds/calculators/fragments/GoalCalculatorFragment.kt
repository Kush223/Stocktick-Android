package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.GoalCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow

class GoalCalculatorFragment: Fragment(R.layout.goal_calculator_fragment_layout) {
    private lateinit var binding: GoalCalculatorFragmentLayoutBinding

    //input variables
    private lateinit var etCurrentFinancialGoalCost: NeumorphEditText
    private lateinit var etInflation: NeumorphEditText
    private lateinit var etCurrentAge: NeumorphEditText
    private lateinit var etMaturityAge: NeumorphEditText
    private lateinit var etCurrentInvestment: NeumorphEditText
    private lateinit var etExpectedReturns: NeumorphEditText

    //output variables
    private lateinit var tvFutureCost: TextView
    private lateinit var tvAppreciation: TextView
    private lateinit var tvDeficitCorpus: TextView
    private lateinit var tvLumpsumRequired: TextView
    private lateinit var tvMonthlyInvestment: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = GoalCalculatorFragmentLayoutBinding.bind(view)

        //input views initialization
        etCurrentFinancialGoalCost = binding.etCurrentCostGoal
        etInflation = binding.etInflation
        etCurrentAge = binding.etCurrentAge
        etMaturityAge = binding.etMaturityAge
        etCurrentInvestment = binding.etCurrentInvestment
        etExpectedReturns = binding.etExpectedReturn

        //output field initializations
        tvFutureCost = binding.tvResFutureCostOfGoal
        tvAppreciation = binding.tvResultAppreciationOfInvestments
        tvDeficitCorpus  = binding.tvResultDeficitCorpus
        tvLumpsumRequired  = binding.tvResultLumpsum
        tvMonthlyInvestment = binding.tvResultInvestmentRequired

        //listening
        etCurrentFinancialGoalCost.listen()
        etInflation.listen()
        etCurrentAge.listen()
        etMaturityAge.listen()
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
            //input parameters
            val ga = etCurrentFinancialGoalCost.getText().toDouble()
            val i = etInflation.getText().toDouble()
            val t = etMaturityAge.getText().toDouble() - etCurrentAge.getText().toDouble()
            val ia = etCurrentInvestment.getText().toDouble()
            val r = etExpectedReturns.getText().toDouble()

            //calculation
            val fv = ga * (1+i/100).pow(t)
            val sip = (fv-ia*(1+r/1200).pow((t+1)*12)/(1-r/1200)*(r/1200+1)) /( ((((1+r/1200).pow(t*12)-1)*1200/r)*(r/1200+1)))




            //formatting the output
            val sFutureValue ="₹${String.format("%.2f",fv)}"
            val sSip = "₹${String.format("%.2f",sip)}"

            //Writing the result the result
            tvFutureCost.text = sFutureValue
            tvMonthlyInvestment.text = sSip




        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }

}