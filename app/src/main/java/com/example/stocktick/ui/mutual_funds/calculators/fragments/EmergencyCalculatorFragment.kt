package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.EmergencyCalculatorFragmentLayoutBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import kotlin.math.pow

class EmergencyCalculatorFragment : Fragment(R.layout.emergency_calculator_fragment_layout){
    private lateinit var binding: EmergencyCalculatorFragmentLayoutBinding

    //input fields
    private lateinit var etExpense: NeumorphEditText
    private lateinit var etEmergencyFundFor: NeumorphEditText
    private lateinit var etEmergencyFundIn: NeumorphEditText
    private lateinit var etInvestmentReturn: NeumorphEditText


    //output fields
    private lateinit var tvEmergencyFund: TextView
    private lateinit var tvMonthlyInvestments: TextView




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EmergencyCalculatorFragmentLayoutBinding.bind(view)
        //field initializations
        etExpense = binding.etLivingExpenses
        etEmergencyFundFor = binding.etEmergencyMonths
        etEmergencyFundIn = binding.etAccumulationMonths
        etInvestmentReturn = binding.etExpectedReturn

        tvEmergencyFund = binding.tvResEmergencyFund
        tvMonthlyInvestments = binding.tvResMonthlyInvestment


        etExpense.listen()
        etEmergencyFundIn.listen()
        etEmergencyFundFor.listen()
        etInvestmentReturn.listen()


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
            val le = etExpense.getText().toDouble()
            val fm = etEmergencyFundFor.getText().toDouble()
            val im = etEmergencyFundIn.getText().toDouble()
            var r = etInvestmentReturn.getText().toDouble()


            //calculation
            val eFund = le*fm
            r /= 1200
            val sip = eFund / ((((1+r).pow(im)-1)/r)*(r+1))


            //formatting the output
            val sEFund ="₹${String.format("%.2f",eFund)}"
            val sSip = "₹${String.format("%.2f",sip)}"

            //Writing the result the result
            tvEmergencyFund.text = sEFund
            tvMonthlyInvestments.text = sSip





        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }

}