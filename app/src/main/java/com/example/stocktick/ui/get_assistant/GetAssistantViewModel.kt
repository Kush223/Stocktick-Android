package com.example.stocktick.ui.get_assistant

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stocktick.models.UserProfile
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "GetAssistantViewModel"
class GetAssistantViewModel(application: Application) : AndroidViewModel(application) {
    private var tokenSharedPreference: String
    var message: String = ""


    val userDetail: MutableLiveData<UserProfile>  by lazy {
        MutableLiveData<UserProfile>()
    }

    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
        updateUserDetails()
    }

    fun postQuery(
    userQuery: QueryModel,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postQuery(
                        authToken = tokenSharedPreference,
                        userQuery
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

    fun updateUserDetails(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getUserDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        userDetail.value = body
                        /*binding.tvName.text = body.name
                        binding.tvEmail.text = body.email
                        binding.tvPhoneNo.text = body.phone
                        binding.tvGender.text = body.gender
                        binding.tvAge.text = "${body.age.toString()} yrs"
                        Glide.with(requireActivity()).load(body.profile_url).into(binding.profileImage)*/
                    }
                }
                else {

                }
            } catch (e: Exception){

            }
        }
    }
}