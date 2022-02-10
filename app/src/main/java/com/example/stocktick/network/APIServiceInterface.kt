package com.example.stocktick.network

import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.auth.model.ProfileModel
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServiceInterface {

    @POST("auth/getotp/")
    suspend fun getOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @POST("auth/validateotp/")
    suspend fun validateOtp(@Body phoneModel: PhoneModel): GetOtpModel

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