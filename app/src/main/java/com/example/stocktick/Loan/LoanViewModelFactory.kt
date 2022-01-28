package com.example.stocktick.Loan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.ui.home.HomeViewModel

class LoanViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((HomeViewModel::class.java))){
            return HomeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}