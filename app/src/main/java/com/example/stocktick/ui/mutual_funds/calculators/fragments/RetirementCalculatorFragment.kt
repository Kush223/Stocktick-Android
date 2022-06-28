package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.RetirementCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import java.util.*
import kotlin.math.pow

class RetirementCalculatorFragment: Fragment(R.layout.retirement_calculator_fragment_layout
) {
    private lateinit var binding : RetirementCalculatorFragmentLayoutBinding

    //input views
    private lateinit var etCurrentMonthlyExpenses : NeumorphEditText
    private lateinit var etInflation : NeumorphEditText
    private lateinit var etCurrentAge : NeumorphEditText
    private lateinit var etRetirementAge : NeumorphEditText
    private lateinit var etLifeExpectancy : NeumorphEditText
    private lateinit var etCurrentInvestment : NeumorphEditText
    private lateinit var etRateOfInterest : NeumorphEditText

    //output fields
    private lateinit var tvRetirementCorpus : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RetirementCalculatorFragmentLayoutBinding.bind(view)

        //view initializations
        etCurrentMonthlyExpenses = binding.etCurrentMonthlyExpense
        etInflation = binding.etInflation
        etCurrentAge = binding.etCurrentAge
        etRetirementAge = binding.etRetirementAge
        etLifeExpectancy = binding.etLifeExpectancy
        etCurrentInvestment  = binding.etCurrentInvestment
        etRateOfInterest = binding.etRateOfReturn

        tvRetirementCorpus = binding.tvRetirementCorpus

        etCurrentMonthlyExpenses.listen()
        etInflation.listen()
        etRetirementAge.listen()
        etCurrentAge.listen()
        etLifeExpectancy.listen()
        etCurrentInvestment.listen()
        etRateOfInterest.listen()


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
            val currentMonthlyExpense = etCurrentMonthlyExpenses.getText().toDouble()
            val inflation = etInflation.getText().toDouble()
            val currentAge = etCurrentAge.getText().toDouble()
            val retirementAge = etRetirementAge.getText().toInt()
            val lifeExpectancy = etLifeExpectancy.getText().toInt()
            val initialInvestment = etCurrentInvestment.getText().toDouble()
            val rateOfReturn = etRateOfInterest.getText().toDouble()

            var monthlyExpenseAtFirstRetirementYear = currentMonthlyExpense * (1 + inflation/100).pow(retirementAge-currentAge)
            println("Then monthly expense :$monthlyExpenseAtFirstRetirementYear")
            //Its just simple compound interest formula

            var retirementMoney = 0.0

            val stack : Stack<Double> = Stack()

            for (i in retirementAge until lifeExpectancy){
                stack.push(monthlyExpenseAtFirstRetirementYear*12)
                monthlyExpenseAtFirstRetirementYear *= 1 + inflation/100
            }
            var element = stack.pop()
            while (element != null){
                retirementMoney = retirementMoney/(1+rateOfReturn/100) + element
                element = try {stack.pop()}
                catch (e  : EmptyStackException){
                    null
                }

            }

            val sRetirementCorpus = "₹${String.format("%.2f",retirementMoney)}"
            tvRetirementCorpus.text = sRetirementCorpus



        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}
