package com.example.stocktick.network

import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.ui.education.ResponseRegisterWebinar
import com.example.stocktick.ui.education.model.BlogItem
import com.example.stocktick.ui.education.model.RegisterWebinarModel
import com.example.stocktick.ui.education.model.WebinarItem
import com.example.stocktick.ui.insurance.InsuranceModel
import com.example.stocktick.ui.loan.LoanFormItem
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServiceInterface {

    //OTP
    @POST("/auth/getotp/")
    suspend fun getOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @POST("/auth/validateotp/")
    suspend fun validateOtp(@Body phoneModel: PhoneModel): GetOtpModel

    //LOAN
    @GET("/get/loans/")
    suspend fun getLoans(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>


    @POST("/add/loandetails/")
    suspend fun addLoanDetails(@Header("authToken") authToken: String,@Body loanFormItem: LoanFormItem): Response<GetOtpModel>

    //INSURANCE
    @GET("/get/Insurance/")
    suspend fun getInsurances(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

    @POST("/add/insurance/details/")
    suspend fun addInsuranceDetails(@Header("authToken") authToken: String,@Body insuranceModel: InsuranceModel): Response<GetOtpModel>

    //EDUCATION
    @GET("/get/education/")
    suspend fun getBlogs(@Header("authToken") authToken: String): Response<List<BlogItem>>

    @GET("/get/webinars/")
    suspend fun getWebinar(@Header("authToken") authToken: String): Response<List<WebinarItem>>

    @POST("/subscribe/webinar/")
    suspend fun postRegisterToWebinar(@Header("authToken")authToken: String ,@Body registerWebinar : RegisterWebinarModel): Response<ResponseRegisterWebinar>
}
//JSON STRUCTURE AT https://codeshare.io/wn39rK for education pages
