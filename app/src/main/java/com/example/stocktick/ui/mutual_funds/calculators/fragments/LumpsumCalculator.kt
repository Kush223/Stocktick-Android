package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentLumpsumCalculatorBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow


class LumpsumCalculator : Fragment(R.layout.fragment_lumpsum_calculator) {
    private lateinit var binding: FragmentLumpsumCalculatorBinding
    //input fields
    private lateinit var etTotalValue: NeumorphEditText
    private lateinit var etExpectedReturn: NeumorphEditText
    private lateinit var etTimePeriod: NeumorphEditText


    //output fields
    private lateinit var tvInvestedAmount: TextView
    private lateinit var tvEstimatedReturns: TextView
    private lateinit var tvTotalValue: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLumpsumCalculatorBinding.bind(view)

        //field initializations
        etTotalValue = binding.etTotalInvestment
        etExpectedReturn = binding.etExpectedReturnRate
        etTimePeriod = binding.etTimePeriod

        tvInvestedAmount = binding.tvResInvestedAmount
        tvEstimatedReturns = binding.tvResEstimatedReturns
        tvTotalValue = binding.tvResTotalValue

        etTotalValue.listen()
        etExpectedReturn.listen()
        etTimePeriod.listen()

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
            val p = etTotalValue.getText().toDouble()
            val r = etExpectedReturn.getText().toDouble()
            val t = etTimePeriod.getText().toDouble()

            //calculation
            val totalValue = p * (1+r/100).pow(t)
            val estReturn = totalValue - p


            //formatting the output
            val sTotalInvestment ="₹${String.format("%.2f",p)}"
            val sEstReturn = "₹${String.format("%.2f",estReturn)}"
            val sTotalValue = "₹${String.format("%.2f",totalValue)}"

            //Writing the result the result
            tvInvestedAmount.text = sTotalInvestment
            tvEstimatedReturns.text = sEstReturn
            tvTotalValue.text = sTotalValue




        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }

}