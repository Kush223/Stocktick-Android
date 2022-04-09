package com.example.stocktick.ui.mutual_funds.risk_factor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.databinding.ActivityMutualFundsBinding
import com.example.stocktick.ui.customviews.CustomTracker
import com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment.Question

class RiskFactorActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMutualFundsBinding
    lateinit var customTracker: CustomTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMutualFundsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customTracker = binding.tracker
        customTracker.setDivisions(2)

    }

}