package com.example.stocktick.ui.notifications

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.ui.dashboard.DashboardViewModel

class DashboardViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((DashboardViewModel::class.java))) {
            return DashboardViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}