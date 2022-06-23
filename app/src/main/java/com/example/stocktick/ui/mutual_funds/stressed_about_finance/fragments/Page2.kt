package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage2Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page2Dto
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlinx.coroutines.*
import soup.neumorphism.NeumorphButton

private const val TAG = "Page2"

class Page2 : Fragment(R.layout.fragment_page2) {
    private lateinit var binding: FragmentPage2Binding
    private lateinit var pieChart: AnimatedPieView
    private lateinit var etInvestAmount: NeumorphEditText
    private lateinit var etHouseholdExpenses: NeumorphEditText
    private lateinit var etLifestyleExpenses: NeumorphEditText
    private lateinit var etSurplus: NeumorphEditText
    private lateinit var etTax: NeumorphEditText
    private lateinit var etEmi: NeumorphEditText

    private lateinit var btNext: NeumorphButton

    private var investmentAmount = 5000.0
    private var householdExpenses = 5000.0
    private var lifestyleExpenses = 5000.0
    private var surplus = 5000.0
    private var taxPaid = 5000.0
    private var emi = 5000.0

    private fun Double.getPercentage(): String {
        val p = this*100/(investmentAmount+householdExpenses+lifestyleExpenses+surplus+taxPaid+emi)
        return  "(${p.toInt()}%)"
    }


    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage2Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(1)
        etInvestAmount = binding.etInvestmentAmount
        etHouseholdExpenses = binding.etHousehold
        etLifestyleExpenses = binding.etLifestyle
        etSurplus = binding.etSurplus
        etTax = binding.etTax
        etEmi = binding.etEmi
        pieChart = binding.pieChart

        btNext = binding.btNext

        pieChart.applyConfig(
            getConfig(duration = 500)
        )
        pieChart.start()


        //setting initial values in edit-texts
        etInvestAmount.setText(investmentAmount.toString())
        etHouseholdExpenses.setText(householdExpenses.toString())
        etLifestyleExpenses.setText(lifestyleExpenses.toString())
        etSurplus.setText(surplus.toString())
        etTax.setText(taxPaid.toString())
        etEmi.setText(emi.toString())

        autofill()




        var job: Job? = null
        etInvestAmount.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    investmentAmount = it.toDouble()

                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }

        }
        etHouseholdExpenses.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    householdExpenses = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etLifestyleExpenses.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    lifestyleExpenses = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etSurplus.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    surplus = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etTax.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    taxPaid = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etEmi.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    emi = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }

        btNext.setOnClickListener{
            handleOnClick()

        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page2_to_page3)
        }



    }

    private fun handleOnClick() {
        viewModel.postPage2(
           page2Dto = Page2Dto(
               emi_paid = emi.toInt(),
               household_expns = householdExpenses.toInt(),
               invst_amount = investmentAmount.toInt(),
               lifestyle_expns = lifestyleExpenses.toInt(),
               surplus = surplus.toInt(),
               tax_paid = taxPaid.toInt()
           )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page2_to_page3)
            } else {
                view?.findNavController()?.navigate(R.id.action_page2_to_page3) //remove it later
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getConfig(
        investmentAmt: Double= investmentAmount,
        household: Double= householdExpenses,
        lifestyle: Double = lifestyleExpenses,
        surplus: Double = this.surplus,
        tax: Double= taxPaid,
        emi: Double= this.emi,
        duration: Long = 50
    ) : AnimatedPieViewConfig {
        return AnimatedPieViewConfig().apply {
            startAngle(90f)
            addData(
                SimplePieInfo(
                    emi,
                    Color.parseColor("#FF0000"),
                    "EMI\n ${emi.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    investmentAmt,
                    Color.parseColor("#0032E5"),
                    "Investment\n Amt ${investmentAmt.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    household,
                    Color.parseColor("#EED600"),
                    "Household\n ${household.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    lifestyle,
                    Color.parseColor("#04B500"),
                    "Lifestyle\n ${lifestyle.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    surplus,
                    Color.parseColor("#FC7900"),
                    "Surplus\n ${surplus.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    tax,
                    Color.parseColor("#FF7EFA"),
                    "Taxes\n ${tax.getPercentage()}"
                )
            )
            autoSize(true)
            drawText(true)
            textSize(30f)
            pieRadius(200f)

            duration(duration)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun autofill(){
        viewModel.getPage2{ isSuccessful, page2 ->
            if (isSuccessful && page2!=null){
                etInvestAmount.setText(page2!!.invst_amount.toString())
                etHouseholdExpenses.setText(page2!!.household_expns.toString())
                etLifestyleExpenses.setText(page2!!.lifestyle_expns.toString())
                etSurplus.setText(page2!!.surplus.toString())
                etEmi.setText(page2!!.emi_paid.toString())
                etTax.setText(page2!!.tax_paid.toString())

            }


        }
    }


}