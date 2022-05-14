package com.example.stocktick.ui.mutual_funds.stressed_about_finance

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Page2
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.*
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
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                        RetrofitClientInstance.retrofitService.postPage1(
                                authToken = tokenSharedPreference,
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

    fun postPage2(
        page2Dto: Page2Dto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage2 :$page2Dto , token :$tokenSharedPreference")

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postPage2(
                        tokenSharedPreference,
                        page2Dto
                    )
                Log.d(TAG, "postUserResponse: $response")
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


    fun postPage3(
        page3Dto: Page3Dto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage3 :$page3Dto , token :$tokenSharedPreference")

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postPage3(
                        tokenSharedPreference,
                        page3Dto
                    )
                Log.d(TAG, "postUserResponse: $response")
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


    fun postPage4(
        page4Dto: Page4Dto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage4 :$page4Dto , token :$tokenSharedPreference")

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postPage4(
                        tokenSharedPreference,
                        page4Dto
                    )
                Log.d(TAG, "postUserResponse: $response")
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



    fun postPage5(
        page5Dto: Page5Dto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage5 :$page5Dto , token :$tokenSharedPreference")

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postPage5(
                        tokenSharedPreference,
                        page5Dto
                    )
                Log.d(TAG, "postUserResponse: $response")
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



    fun postPage6(
        page6Dto: Page6Dto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postPage6 :$page6Dto , token :$tokenSharedPreference")

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postPage6(
                        tokenSharedPreference,
                        page6Dto
                    )
                Log.d(TAG, "postUserResponse: $response")
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

    fun getPage1(onResponse: (
        isSuccessful: Boolean,
        page1: com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Page1?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage1(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty()
                ){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                                com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Page1(
                                    response.body()!![0]?.lstatus,
                                    response.body()!![0].children,
                                    response.body()!![0].parents,
                                )

                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }

    fun getPage2(onResponse: (
        isSuccessful: Boolean,
        page2: Page2?

    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage2(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && !response.body().isNullOrEmpty()){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            Page2(
                                emi_paid = response.body()!![0].emi_paid ?: 0,
                                household_expns = response.body()!![0].household_expns ?: 0,
                                invst_amount = response.body()!![0].invst_amount ?: 0,
                                lifestyle_expns = response.body()!![0].lifestyle_expns ?: 0,
                                surplus = response.body()!![0].surplus ?: 0,
                                tax_paid = response.body()!![0].tax_paid ?: 0,
                            )
                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }

    fun getPage3(onResponse: (
        isSuccessful: Boolean,
        page3Dto: Page3Dto?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage3(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            response.body()
                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }

    fun getPage4(onResponse: (
        isSuccessful: Boolean,
        page4Dto: Page4Dto?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage4(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            response.body()
                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }

    fun getPage5(onResponse: (
        isSuccessful: Boolean,
        page5Dto: Page5Dto?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage5(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            response.body()
                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }

    fun getPage6(onResponse: (
        isSuccessful: Boolean,
        page6Dto: Page6Dto?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getPage6(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            response.body()
                        )
                    }
                }
                else {
                    withContext(Dispatchers.Main){
                        onResponse(
                            false,
                            null
                        )
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }
        }
    }




}
