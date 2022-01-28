package com.example.stocktick.Network

import com.example.stocktick.LoginSignup.Models.GetOtpModel
import com.example.stocktick.LoginSignup.Models.PhoneModel
import com.example.stocktick.LoginSignup.Models.ProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface JsonPlaceholderApi {

    @POST("auth/getotp/")
    fun getOtp(@Body phoneModel: PhoneModel): Call<GetOtpModel>

    @POST("auth/validateotp/")
    fun validateOtp(@Body phoneModel: PhoneModel): Call<GetOtpModel>


    @POST("update/userinfo/")
    fun updateInfo(
        @Header("authToken") authToken: String,
        @Body profileModel: ProfileModel
    ): Call<GetOtpModel>
}