package com.example.stocktick.ui.notifications

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotificationsViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((NotificationsViewModel::class.java))) {
            return NotificationsViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}