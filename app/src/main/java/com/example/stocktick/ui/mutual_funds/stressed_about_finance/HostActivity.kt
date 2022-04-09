package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityHostBinding
import com.example.stocktick.ui.customviews.CustomTracker

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    lateinit var customTracker: CustomTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customTracker = binding.tracker
        customTracker.setDivisions(7)
        customTracker.setPosition(0)
    }
}