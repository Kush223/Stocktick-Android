package com.example.stocktick.network

import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.ui.education.model.BlogItem
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

    @POST("/auth/getotp/")
    suspend fun getOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @POST("/auth/validateotp/")
    suspend fun validateOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @GET("/get/loans/")
    suspend fun getLoans(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

    @GET("/get/Insurance/")
    suspend fun getInsurances(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<LoanItem>>

//EDUCATION PAGE URLS
    @GET("/get/education/")
    suspend fun getBlogs(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<BlogItem>>

    @GET("/get/webinar")
    suspend fun getWebinar(@Header("authToken") authToken: String,@Header("platform") platform: String): Response<List<WebinarItem>>


    @POST("/add/loandetails/")
    suspend fun addLoanDetails(@Header("authToken") authToken: String,@Body loanFormItem: LoanFormItem): Response<GetOtpModel>

    @POST("/add/insurance/details/")
    suspend fun addInsuranceDetails(@Header("authToken") authToken: String,@Body insuranceModel: InsuranceModel): Response<GetOtpModel>

//    @POST("subscribe/webinar/")
//    suspend fun registerToWebinar(@Header("authToken")authToken: String,@Body subscribeWebinar: )
//POST request only returns only the message....

}
//JSON STRUCTURE AT https://codeshare.io/wn39rK
