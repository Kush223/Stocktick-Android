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
import br.com.felix.horizontalbargraph.HorizontalBar
import br.com.felix.horizontalbargraph.model.BarItem
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage4Binding
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import kotlinx.coroutines.*


private const val TAG = "Page4"
class Page4 : Fragment(R.layout.fragment_page4) {
    private lateinit var binding: FragmentPage4Binding

    private lateinit var hBarGraph: HorizontalBar

    private var fd = 5000.0
    private var mf = 5000.0
    private var epf = 5000.0
    private var shares = 5000.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage4Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(3)
        hBarGraph = binding.hBarGraph
        hBarGraph.init(requireContext()).apply {
            add(BarItem("FD", fd, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("MF's", mf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("EPF/NPS", epf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("Share", shares, Color.parseColor("#30BA00"), Color.WHITE))
        }
            .build()



        var job: Job? = null
        binding.etFixedDeposit.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    fd = it.toDouble()
                    update()

                }
            }

        }
        binding.etMutualFunds.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    mf = it.toDouble()
                    update()
                }
            }
        }
        binding.etEpf.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    epf = it.toDouble()
                    update()
                }
            }
        }
        binding.etShares.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    shares = it.toDouble()
                    update()
                }
            }
        }

        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page4_to_page5)
        }

    }

    private fun update(){
        hBarGraph.removeAll()
        hBarGraph.apply {
            add(BarItem("FD", fd, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("MF's", mf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("EPF/NPS", epf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("Share", shares, Color.parseColor("#30BA00"), Color.WHITE))
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