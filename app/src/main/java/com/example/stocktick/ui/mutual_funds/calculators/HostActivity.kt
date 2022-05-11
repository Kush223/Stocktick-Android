package com.example.stocktick.ui.mutual_funds.calculators

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stocktick.databinding.ActivityHost2Binding

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHost2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHost2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}