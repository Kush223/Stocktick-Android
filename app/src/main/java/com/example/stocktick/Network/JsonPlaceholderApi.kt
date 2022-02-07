package com.example.stocktick.Network

import com.example.stocktick.LoginSignup.Models.GetOtpModel
import com.example.stocktick.LoginSignup.Models.PhoneModel
import com.example.stocktick.LoginSignup.Models.ProfileModel
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface JsonPlaceholderApi {

    @POST("auth/getotp/")
    fun getOtp(@Body phoneModel: PhoneModel): Call<GetOtpModel>
    //PHONEMODEL AS str = phone, otp
    //GETOTPMODEL AS str = message,authToken,old_user

    //HEROKUAPP MODEL
    //get/all/otp "phone": "9140876745",
    //        "otp": 186693
    //@POST auth/getotp/ "username" :

    //@POST /auth/validateotp/ "username" : "<phone_number>", "otp" : "<otp resp>"


    @POST("auth/validateotp/")
    fun validateOtp(@Body phoneModel: PhoneModel): Call<GetOtpModel>

    @POST("update/userinfo/")
    fun updateInfo(
        @Header("authToken") authToken: String, @Body profileModel: ProfileModel
    ): Call<GetOtpModel>

    @GET("get/loans/")
    fun getLoans(@Header("authToken") authToken: String): Call<List<LoanItem>>

    @GET("get/Insurance/")
    fun getInsurances(@Header("authToken") authToken: String): Call<List<LoanItem>>

    @GET("get/education")
    fun getEducations(@Header("authToken") authToken: String): Call<List<LoanItem>>

    @GET("get/media")
    fun getMedias(@Header("authToken") authToken: String): Call<List<LoanItem>>

//    @GET("get/all/otp")
//    fun getAllOtp(@Header("authToken") authToken: String): Call<List>
//

//    @GET("posts/{num}")
//    suspend fun getPostById(@Path("num") num : Int): Response
//
//    @GET("comments")
//    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response>
//
//    @POST("posts")
//    suspend fun createPost(@Body post: Post): Response
}