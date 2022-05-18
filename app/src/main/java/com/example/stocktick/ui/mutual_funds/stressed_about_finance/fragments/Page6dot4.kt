package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot4Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import kotlin.math.pow


class Page6dot4 : Fragment(R.layout.fragment_page6dot4) {
    private lateinit var binding: FragmentPage6dot4Binding

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
        binding = FragmentPage6dot4Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(9)
        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot4_to_page7)
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot4_to_page7)
        }

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