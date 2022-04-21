package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        customTracker.setDivisions(11)
        customTracker.setPosition(0)
    }
}