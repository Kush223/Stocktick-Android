package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.EmiCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow


private const val TAG = "EmiCalculatorFragment"
class EmiCalculatorFragment : Fragment(R.layout.emi_calculator_fragment_layout){
    private lateinit var binding: EmiCalculatorFragmentLayoutBinding

    private lateinit var loanAmount: NeumorphEditText
    private lateinit var rateOfInterest: NeumorphEditText
    private lateinit var loanTenure: NeumorphEditText

    // results parameters
    private var emi  = 0.0

    //Output widgets
    private lateinit var monthlyEmi: TextView
    private lateinit var principalAmount: TextView
    private lateinit var totalInterest: TextView
    private lateinit var totalAmount: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EmiCalculatorFragmentLayoutBinding.bind(view)

        //view initializations
        loanAmount = binding.etAmount
        rateOfInterest = binding.etTimePeriod
        loanTenure = binding.etCurrentSaving

        monthlyEmi = binding.tvMonthlyEmi
        principalAmount = binding.tvPrincipalAmount
        totalInterest = binding.tvTotalInterest
        totalAmount = binding.tvTotalAmount


        loanAmount.onTextChangeListener {
            calculate()
        }
        rateOfInterest.onTextChangeListener {
            calculate()
        }
        loanTenure.onTextChangeListener {
            calculate()
        }





    }


    private fun calculate(){
        try {
            val p = loanAmount.getText().toDouble()
            val n = loanTenure.getText().toDouble()*12
            val r = rateOfInterest.getText().toDouble()/1200

            emi = (p*r* (1 + r).pow(n))/((1+r).pow(n) - 1)

            val tAmount = emi*n
            val tInterest = tAmount -  p

            //Strings
            val sEmi ="₹${String.format("%.2f",emi)}"
            val sPrincipalAmount = "₹${String.format("%.2f",p)}"
            val sTotalInterest = "₹${String.format("%.2f",tInterest)}"
            val sTotalAmount = "₹${String.format("%.2f",tAmount)}"

                monthlyEmi.text = sEmi
            principalAmount.text = sPrincipalAmount
            totalInterest.text = sTotalInterest
            totalAmount.text = sTotalAmount
        }
        catch (e: ArithmeticException){
            Log.e(TAG, "calculate: Arithmetic Exception", )
        }
        catch (e: NumberFormatException){
            Log.d(TAG, "calculate: Number format Exception")
        }
    }

}