package com.example.stocktick.ui.insurance

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.ui.home.HomeViewModel
import com.example.stocktick.ui.insurance.InsuranceViewModel

class InsuranceViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((InsuranceViewModel::class.java))){
            return InsuranceViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}