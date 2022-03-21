package com.example.stocktick.ui.education

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EducationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((EducationViewModel::class.java))) {
            return EducationViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}