package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.SipTopUpCalculatoFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText

class SipTopUpFragment : Fragment(R.layout.sip_top_up_calculato_fragment_layout) {
    private lateinit var binding: SipTopUpCalculatoFragmentLayoutBinding

    //input fields
    private lateinit var etMonthlyInvestment: NeumorphEditText
    private lateinit var etAnnualStepUp: NeumorphEditText
    private lateinit var etExpectedReturnRate: NeumorphEditText
    private lateinit var etTimePeriod: NeumorphEditText

    //output fields
    private lateinit var tvInvestedAmount: TextView
    private lateinit var tvEstReturns: TextView
    private lateinit var tvTotalValue: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SipTopUpCalculatoFragmentLayoutBinding.bind(view)

        //view initializations
        etMonthlyInvestment = binding.etMonthlyInvestment
        etAnnualStepUp = binding.etAnnualStepUp
        etExpectedReturnRate = binding.etExpectedReturnRate
        etTimePeriod = binding.etTimePeriod

        tvInvestedAmount = binding.tvResInvestedAmount
        tvEstReturns = binding.tvResEstimatedReturns
        tvTotalValue = binding.tvResTotalValue

        etMonthlyInvestment.listen()
        etAnnualStepUp.listen()
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
            //input parameters
            val x = etMonthlyInvestment.getText().toDouble()
            val sr = etAnnualStepUp.getText().toDouble()
            val r = etExpectedReturnRate.getText().toDouble()
            val t = etTimePeriod.getText().toInt()



            //calculation
            var monthlyInvestment = x
            var totalInvestment = 0.0
            var monthlyReturn = r/(1200.0)
            var totalReturn = 0.0;
            for (i in 0 until t){
                for (i in 1..12){
                    totalInvestment += monthlyInvestment
                    totalReturn =( totalReturn + monthlyInvestment)* (1+monthlyReturn)
                }
                monthlyInvestment *= 1 + sr/100
            }
//            val lastYearMonthlyInvestment = monthlyInvestment/(1+sr/100)
//            totalReturn-=lastYearMonthlyInvestment
//            totalInvestment -= lastYearMonthlyInvestment



            //formatting the output
            val sTotalInvestment = "₹${String.format("%.2f",totalInvestment)}"
            val sFinalAmount = "₹${String.format("%.2f",totalReturn)}"
            val sNetProfit = "₹${String.format("%.2f",totalReturn - totalInvestment)}"

            //Writing the result the result

            tvInvestedAmount.text = sTotalInvestment
            tvTotalValue.text = sFinalAmount
            tvEstReturns.text = sNetProfit


        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }

}