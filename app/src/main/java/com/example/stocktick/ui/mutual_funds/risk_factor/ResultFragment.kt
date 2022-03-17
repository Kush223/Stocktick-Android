package com.example.stocktick.ui.mutual_funds.risk_factor

import android.os.Bundle
import android.os.PerformanceHintManager
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentResultBinding
import com.example.stocktick.ui.customviews.PerformanceLabel
import com.example.stocktick.ui.customviews.PerformanceMeter


class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private lateinit var meter : PerformanceMeter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        meter = binding.performanceMEter
        meter.swapAngle(PerformanceLabel.WEALTH_MULTIPLY)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}