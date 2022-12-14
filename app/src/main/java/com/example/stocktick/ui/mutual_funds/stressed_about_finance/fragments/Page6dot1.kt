package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot1Binding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator.RetirementCalculator
import com.example.stocktick.utility.Constant
import com.example.stocktick.utility.Utility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.pow

private const val TAG = "Page6dot1"
class Page6dot1 : Fragment(R.layout.fragment_page6dot1) {
    private lateinit var binding: FragmentPage6dot1Binding
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



    private val postDataModel : RetirementCalculator = RetirementCalculator()


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
                    RetrofitClientInstance.retrofitService.postRetirementCalculator(
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
        binding = FragmentPage6dot1Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(6)
        binding.btNext.setOnClickListener{
            postData { 
                if (it) {
                    view?.findNavController()?.navigate(R.id.action_page6dot1_to_page6dot2)
                }
                else Toast.makeText(requireContext(), "Something went wrong\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot1_to_page6dot2)
        }


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
            val sRetirementCorpus = "???${String.format("%.2f",retirementMoney)}"
            val sAppreciation = "???${String.format("%.2f",appreciation)}"
            val sDeficitCorpus = "???${String.format("%.2f",deficitCorpus)}"
            val sS = "???${String.format("%.2f",s)}"
            val sLumpsum = "???${String.format("%.2f",lumpsum)}"

            tvRetirementCorpus.text = sRetirementCorpus
            binding.tvAppreciation.text = sAppreciation
            binding.tvDeficitCorpus.text = sDeficitCorpus
            binding.tvMonthlyInvestment.text = sS
            binding.tvLumpsumFundingRequired.text = sLumpsum

            postDataModel.inflationRate = inflation
            postDataModel.age = currentAge
            postDataModel.retirementAge = retirementAge.toDouble()
            postDataModel.lifeExpectancy = lifeExpectancy.toDouble()
            postDataModel.currentInvestment = initialInvestment
            postDataModel.returnRate = rateOfReturn
            postDataModel.investmentAppreciation = appreciation
            postDataModel.corpusDeficit = deficitCorpus
            postDataModel.lumpsumFundReqd = lumpsum
            postDataModel.monthlyInvestmentReqd = s


        }
        catch (e: ArithmeticException){
        }
        catch (e: NumberFormatException){
        }
    }
}