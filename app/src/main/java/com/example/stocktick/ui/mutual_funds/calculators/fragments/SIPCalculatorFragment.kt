package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.SipCalculatorFragmentBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow

class SIPCalculatorFragment : Fragment(R.layout.sip_calculator_fragment){
    private lateinit var binding: SipCalculatorFragmentBinding

    //input views
    private lateinit var etMonthlyInvestment: NeumorphEditText
    private lateinit var etExpectedReturnRate: NeumorphEditText
    private lateinit var etTimePeriod: NeumorphEditText


    //output views
    private lateinit var tvInvestmentAmount: TextView
    private lateinit var tvEstAmount: TextView
    private lateinit var tvTotalValue: TextView





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SipCalculatorFragmentBinding.bind(view)

        etMonthlyInvestment = binding.etAmount
        etExpectedReturnRate = binding.etTimePeriod
        etTimePeriod = binding.etCurrentSaving

        tvInvestmentAmount = binding.tvInvestmentAmount
        tvEstAmount = binding.tvEstAmount
        tvTotalValue = binding.tvTotalValue

        etMonthlyInvestment.listen()
        etExpectedReturnRate.listen()
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
            //variables
            val p = etMonthlyInvestment.getText().toDouble()
            val i = etExpectedReturnRate.getText().toDouble()
            val n = etTimePeriod.getText().toDouble()*12

            val totalInvestment = p*n
            val totalReturns = p * (((1+i)*n-1)/i)*(i+1)
            val returns = totalReturns - totalInvestment

            val sTotalReturns ="₹${String.format("%.2f",totalReturns)}"
            val sTotalInvestment = "₹${String.format("%.2f",totalInvestment)}"
            val sReturns = "₹${String.format("%.2f", returns)}"

            tvInvestmentAmount.text = sTotalInvestment
            tvTotalValue.text = sTotalReturns
            tvEstAmount.text= sReturns


        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}