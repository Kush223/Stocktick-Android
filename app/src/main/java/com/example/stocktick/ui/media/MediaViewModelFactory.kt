package com.example.stocktick.ui.media

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MediaViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((MediaViewModel::class.java))) {
            return MediaViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}