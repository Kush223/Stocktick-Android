package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot3Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import kotlin.math.pow


class Page6dot3 : Fragment(R.layout.fragment_page6dot3) {
    private lateinit var binding: FragmentPage6dot3Binding


    private lateinit var etCurrentCostOfMarriage: NeumorphEditText
    private lateinit var etInflation: NeumorphEditText
    private lateinit var etCurrentAge: NeumorphEditText
    private lateinit var etMarriageAge: NeumorphEditText
    // private lateinit var etCurrentInvestment: NeumorphEditText
    private lateinit var etExpectedReturns: NeumorphEditText



    //Output widgets
    private lateinit var tvFutureCost: TextView
    //    private lateinit var tvAppreciation: TextView
//    private lateinit var tvDeficitCorpus: TextView
//    private lateinit var tvLumpsum: TextView
    private lateinit var tvMonthlyInvestmentRequired: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6dot3Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(8)
        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot3_to_page6dot4)
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot3_to_page6dot4)
        }

        //view initializations
        etCurrentCostOfMarriage = binding.etAmount
        etInflation = binding.etTimePeriod
        etCurrentAge = binding.etCurrentSaving
        etMarriageAge = binding.etMonthlySaving
//        etCurrentInvestment = binding.etInvestAmount
        etExpectedReturns = binding.etExpectedReturn


        //output view initialization
        tvFutureCost = binding.futureCostOfMarriage
//        tvAppreciation = binding.appreciation
//        tvDeficitCorpus = binding.deficitCorpus
//        tvLumpsum = binding.lumpsum
        tvMonthlyInvestmentRequired = binding.monthlyEmi


        etInflation.listen()
        etCurrentAge.listen()
        etMarriageAge.listen()
        //etCurrentInvestment.listen()
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
            val i = etInflation.getText().toDouble()
            val n = etMarriageAge.getText().toDouble() - etCurrentAge.getText().toDouble()
            var r = etExpectedReturns.getText().toDouble()

            //calculations
            val fv = pv * (1+i/100).pow(n)
            r /= 1200
            val mi = fv / ((((1+r).pow(n*12)-1)/r)*(r+1))

            //writing to output
            val sFv = "₹${String.format("%.2f",fv)}"
            val sMi = "₹${String.format("%.2f",mi)}"

            tvFutureCost.text = sFv
            tvMonthlyInvestmentRequired.text = sMi

        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}