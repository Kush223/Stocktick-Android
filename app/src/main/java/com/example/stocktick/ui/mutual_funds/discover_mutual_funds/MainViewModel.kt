package com.example.stocktick.ui.mutual_funds.discover_mutual_funds

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.utility.Constant

private const val TAG = "MainViewModelMF"
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var tokenSharedPreference: String

    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
    }
}