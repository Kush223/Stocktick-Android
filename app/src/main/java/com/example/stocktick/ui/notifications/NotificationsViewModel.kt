package com.example.stocktick.ui.notifications

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

class NotificationsViewModel(context: Context) : ViewModel() {
    private val _mText: MutableLiveData<String> = MutableLiveData()
    val mText: LiveData<String>
        get() = _mText

    init {
        _mText.value = "This is notifications fragment"
    }
}