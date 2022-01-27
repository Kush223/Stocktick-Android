package com.example.stocktick.Network

import android.util.JsonToken
import com.example.stocktick.LoginSignup.GetOtpModel
import com.example.stocktick.LoginSignup.PhoneModel
import com.example.stocktick.LoginSignup.ProfileModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface JsonPlaceholderApi {

    @POST("auth/getotp/")
    fun getOtp(@Body phoneModel: PhoneModel): Call<GetOtpModel>

    @POST("auth/validateotp/")
    fun validateOtp(@Body phoneModel: PhoneModel) : Call<GetOtpModel>


    @POST("update/userinfo/")
    fun updateInfo(@Header("authToken") authToken: String,@Body profileModel: ProfileModel) : Call<GetOtpModel>
}