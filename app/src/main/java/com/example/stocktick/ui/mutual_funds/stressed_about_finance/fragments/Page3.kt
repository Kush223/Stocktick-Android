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
import com.example.stocktick.databinding.FragmentPage3Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page3Dto
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import kotlinx.coroutines.*

private const val TAG = "Page3"
class Page3 : Fragment(R.layout.fragment_page3) {
    private lateinit var binding: FragmentPage3Binding
    private lateinit var pieChart: AnimatedPieView

    private lateinit var etGold: NeumorphEditText
    private lateinit var etEquity : NeumorphEditText
    private lateinit var etRealState: NeumorphEditText
    private lateinit var etDebt: NeumorphEditText

    private var gold = 5000.0
    private var equity = 5000.0
    private var realState = 5000.0
    private var debt = 5000.0
    private val viewModel: MainViewModel by activityViewModels()

    private fun Double.getPercentage(): String {
        val p = this*100/(gold + equity + realState + debt )
        return  "(${p.toInt()}%)"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage3Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(2)
        pieChart = binding.pieChart

        etGold = binding.etGold
        etEquity = binding.etEquity
        etRealState = binding.etRealState
        etDebt = binding.etDebt

        pieChart.applyConfig(
            getConfig(duration = 1500)
        )
        pieChart.start()

        //setting values
        etGold.setText(gold.toString())
        etEquity.setText(equity.toString())
        etRealState.setText(realState.toString())
        etDebt.setText(debt.toString())


        autofill()





        var job: Job? = null
        etGold.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    gold = it.toDouble()

                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }

        }
        etEquity.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    equity = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etRealState.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    realState = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }
        etDebt.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    debt = it.toDouble()
                    pieChart.applyConfig(
                        getConfig()
                    )
                    pieChart.start()
                }
            }
        }

        binding.btNext.setOnClickListener{
            handleOnClick()
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page3_to_page4)
        }

    }
    private fun handleOnClick() {
        viewModel.postPage3(
            page3Dto = Page3Dto(
                debt = debt.toInt(),
                equity = equity.toInt(),
                estate = realState.toInt(),
                gold = gold.toInt()
            )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page3_to_page4)
            } else {
                view?.findNavController()?.navigate(R.id.action_page3_to_page4) //remove it later
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getConfig(
        gold: Double= this.gold,
        equity: Double= this.equity,
        realState: Double = this.realState,
        debt: Double = this.debt,
        duration: Long = 50
    ) : AnimatedPieViewConfig {
        return AnimatedPieViewConfig().apply {
            startAngle(90f)
            addData(
                SimplePieInfo(
                    gold,
                    Color.parseColor("#FF0000"),
                    "Gold ${gold.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    equity,
                    Color.parseColor("#04B500"),
                    "Equity ${equity.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    realState,
                    Color.parseColor("#EED600"),
                    "Real State ${realState.getPercentage()}"
                )
            )
            addData(
                SimplePieInfo(
                    debt,
                    Color.parseColor("#0032E5"),
                    "Debt ${debt.getPercentage()}"
                )
            )

            autoSize(true)
            drawText(true)
            textSize(30f)
            startAngle(90f)
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
        viewModel.getPage3{ isSuccessful, page3 ->
            if (isSuccessful && page3!=null){
                etGold.setText(page3!!.gold.toString())
                etEquity.setText(page3!!.equity.toString())
                etRealState.setText(page3!!.estate.toString())
                etDebt.setText(page3!!.debt.toString())

            }


        }
    }


}