package com.example.stocktick.ui.education

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EducationViewModel (context: Context) : ViewModel() {
    private val _mText: MutableLiveData<String> = MutableLiveData()
    val mText: LiveData<String>
        get() = _mText

    init {
        _mText.value = "This is dashboard fragment"
    }
}