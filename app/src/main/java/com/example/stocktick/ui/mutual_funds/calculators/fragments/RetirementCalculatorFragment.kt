package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.RetirementCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.utility.Utility
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

            val appreciation = initialInvestment * (1 + rateOfReturn / 100).pow(retirementAge - currentAge)
            val deficitCorpus = retirementMoney - appreciation

            val m  = (retirementAge - currentAge).toInt() * 12 //no of months
            val mr = rateOfReturn / 12 // monthly return
            var c = 1.0
            Utility.loop(m){
                c = (c * (1 + (mr / 100))) + 1
            }
            val s = retirementMoney /  c


            val lumpsum = s * m  + initialInvestment
            val sRetirementCorpus = "₹${String.format("%.2f",retirementMoney)}"
            val sAppreciation = "₹${String.format("%.2f",appreciation)}"
            val sDeficitCorpus = "₹${String.format("%.2f",deficitCorpus)}"
            val sS = "₹${String.format("%.2f",s)}"
            val sLumpsum = "₹${String.format("%.2f",lumpsum)}"

            tvRetirementCorpus.text = sRetirementCorpus
            binding.tvAppreciation.text = sAppreciation
            binding.tvDeficitCorpus.text = sDeficitCorpus
            binding.tvMonthlyInvestment.text = sS
            binding.tvLumpsumFundingRequired.text = sLumpsum


        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}
