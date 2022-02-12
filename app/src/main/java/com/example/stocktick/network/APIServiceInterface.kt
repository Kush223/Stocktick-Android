package com.example.stocktick.network

import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.auth.model.ProfileModel
import com.example.stocktick.ui.loan.LoanFormItem
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServiceInterface {

    @POST("auth/getotp/")
    suspend fun getOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @POST("auth/validateotp/")
    suspend fun validateOtp(@Body phoneModel: PhoneModel): GetOtpModel

//    @POST("update/userinfo/")
//    fun updateInfo(
//        @Header("authToken") authToken: String,@Header("platform") platform: String, @Body profileModel: ProfileModel
//    ): Call<GetOtpModel>

    @GET("get/loans/")
    suspend fun getLoans(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

    @GET("get/Insurance/")
    suspend fun getInsurances(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

    @GET("get/education/")
    suspend fun getEducations(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

    @POST("add/loandetails/")
    suspend fun addLoanDetails(@Header("authToken") authToken: String,@Body loanFormItem: LoanFormItem): Response<GetOtpModel>

//    @GET("posts/{num}")
//    suspend fun getPostById(@Path("num") num : Int): Response
//
//    @GET("comments")
//    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response>
//
//    @POST("posts")
//    suspend fun createPost(@Body post: Post): Response
}