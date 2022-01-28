package com.example.stocktick.Insurance

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.ui.home.HomeViewModel

class InsuranceViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((HomeViewModel::class.java))){
            return HomeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}