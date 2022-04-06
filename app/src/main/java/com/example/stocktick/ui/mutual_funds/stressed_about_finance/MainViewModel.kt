package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models.AnswersDto
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page1Dto
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var tokenSharedPreference: String

    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
                application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
                sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
    }

    fun postPage1(
            page1Dto: Page1Dto,
            onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage1 :$page1Dto , token :$tokenSharedPreference")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                        RetrofitClientInstance.retrofitService.postPage1(
                                tokenSharedPreference,
                                page1Dto
                        )
                Log.d(TAG, "postUserResponse: ${response.body()}")
                withContext(Dispatchers.Main){
                    onResponse(
                            response.isSuccessful
                    )
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main){
                    onResponse(false)
                }
                Log.d("ERROR", error.toString())
            }
        }
    }
}