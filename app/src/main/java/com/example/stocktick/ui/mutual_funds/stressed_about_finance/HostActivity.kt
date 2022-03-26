package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}