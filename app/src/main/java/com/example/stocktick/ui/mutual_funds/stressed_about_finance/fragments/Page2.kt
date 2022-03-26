package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage2Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.DefaultCirclePieLegendsView
import com.razerdp.widget.animatedpieview.data.IPieInfo
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlinx.coroutines.*

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

    private var investmentAmount = 600.0
    private var householdExpenses = 600.0
    private var lifestyleExpenses = 600.0
    private var surplus = 600.0
    private var taxPaid = 600.0
    private var emi = 600.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage2Binding.bind(view)
        etInvestAmount = binding.etInvestmentAmount
        etHouseholdExpenses = binding.etHousehold
        etLifestyleExpenses = binding.etLifestyle
        etSurplus = binding.etSurplus
        etTax = binding.etTax
        etEmi = binding.etEmi
        pieChart = binding.pieChart


        var job: Job? = null
        etInvestAmount.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }
        etHouseholdExpenses.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }
        etLifestyleExpenses.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }
        etSurplus.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }
        etTax.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }
        etEmi.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                }
            }
            job = null
        }

        val config = AnimatedPieViewConfig().apply {
            startAngle(90f)
            addData(
                SimplePieInfo(
                    30.0,
                    Color.parseColor("#FF0000"),
                    "EMI"
                )
            )
            addData(
                SimplePieInfo(
                    38.0,
                    Color.parseColor("#0032E5"),
                    "Investment Amt"
                )
            )
            addData(
                SimplePieInfo(
                    38.0,
                    Color.parseColor("#EED600"),
                    "Household"
                )
            )
            addData(
                SimplePieInfo(
                    38.0,
                    Color.parseColor("#04B500"),
                    "Lifestyle"
                )
            )
            addData(
                SimplePieInfo(
                    38.0,
                    Color.parseColor("#FC7900"),
                    "Surplus"
                )
            )
            addData(
                SimplePieInfo(
                    38.0,
                    Color.parseColor("#FF7EFA"),
                    "Taxes"
                )
            )
            autoSize(true)
            drawText(true)
            textSize(30f)

            duration(1000)
        }

        pieChart.applyConfig(config)
        pieChart.start()

        lifecycleScope.launch(Dispatchers.Default) {
            delay(5000)
            val config = AnimatedPieViewConfig().apply {
                startAngle(90f)
                addData(
                    SimplePieInfo(
                        30.0,
                        Color.parseColor("#FF0000"),
                        "EMI"
                    )
                )
                addData(
                    SimplePieInfo(
                        38.0,
                        Color.parseColor("#0032E5"),
                        "Investment Amt"
                    )
                )
                addData(
                    SimplePieInfo(
                        38.0,
                        Color.parseColor("#EED600"),
                        "Household"
                    )
                )
                addData(
                    SimplePieInfo(
                        38.0,
                        Color.parseColor("#04B500"),
                        "Lifestyle"
                    )
                )
                addData(
                    SimplePieInfo(
                        5.0,
                        Color.parseColor("#FC7900"),
                        "Surplus"
                    )
                )
                addData(
                    SimplePieInfo(
                        5.0,
                        Color.parseColor("#FF7EFA"),
                        "Taxes"
                    )
                )
                autoSize(true)
                drawText(true)
                textSize(30f)

                duration(10)
            }
            pieChart.applyConfig(config)
            pieChart.start()
        }


    }

    private fun getConfig(
        investmentAmt: Double= investmentAmount,
        household: Double= householdExpenses,
        lifestyle: Double = lifestyleExpenses,
        surplus: Double = this.surplus,
        tax: Double= taxPaid,
        emi: Double= this.emi
    ) {

    }
}