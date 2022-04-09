package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage2Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.DefaultCirclePieLegendsView
import com.razerdp.widget.animatedpieview.data.IPieInfo
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
            getConfig(duration = 1500)
        )
        pieChart.start()


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
            view?.findNavController()?.navigate(R.id.action_page2_to_page3)
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
                    "EMI"
                )
            )
            addData(
                SimplePieInfo(
                    investmentAmt,
                    Color.parseColor("#0032E5"),
                    "Investment Amt"
                )
            )
            addData(
                SimplePieInfo(
                    household,
                    Color.parseColor("#EED600"),
                    "Household"
                )
            )
            addData(
                SimplePieInfo(
                    lifestyle,
                    Color.parseColor("#04B500"),
                    "Lifestyle"
                )
            )
            addData(
                SimplePieInfo(
                    surplus,
                    Color.parseColor("#FC7900"),
                    "Surplus"
                )
            )
            addData(
                SimplePieInfo(
                    tax,
                    Color.parseColor("#FF7EFA"),
                    "Taxes"
                )
            )
            autoSize(true)
            drawText(true)
            textSize(30f)

            duration(duration)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}