package com.example.stocktick.ui.loan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.ui.loan.LoanViewModel

class LoanViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((LoanViewModel::class.java))) {
            return LoanViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}