package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot4Binding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator.ChildMarriageCalculator
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator.EmergencyFundCalculator
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.pow


private const val TAG = "Page6dot4"
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

    private val postDataModel : EmergencyFundCalculator = EmergencyFundCalculator()


    private fun postData(
        onResponse : (isSuccessful : Boolean) -> Unit
    ){

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        val tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postEmergencyFundCalculator(
                        tokenSharedPreference,
                        postDataModel
                    )
                Log.d(TAG, "postUserResponse: ${response.body()}")
                withContext(Dispatchers.Main){
                    onResponse(
                        response.isSuccessful
                    )
                }


            } catch (error: Exception) {
                withContext(Dispatchers.Main){
                    onResponse(false)
                }
                Log.d("ERROR", error.toString())
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6dot4Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(9)
        binding.btNext.setOnClickListener{
            postData {
                if (it) {
                    view?.findNavController()?.navigate(R.id.action_page6dot4_to_page7)
                }
                else Toast.makeText(requireContext(), "Something went wrong\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
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

            postDataModel.livingExpense = etExpense.getText().toDouble()
            postDataModel.emergencyRate = etEmergencyFundFor.getText().toDouble()
            postDataModel.emergencyFund = etEmergencyFundIn.getText().toDouble()
            postDataModel.investmentReturn = etInvestmentReturn.getText().toDouble()
            postDataModel.emergencyFundYouNeed = eFund
            postDataModel.monthlyInvestmentReqd = sip





        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}