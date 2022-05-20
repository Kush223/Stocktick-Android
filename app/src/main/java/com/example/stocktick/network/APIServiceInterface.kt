package com.example.stocktick.network

import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.models.requests.UpdateUserProfileDTO
import com.example.stocktick.models.response.HeadlineModel
import com.example.stocktick.models.response.StandardSuccessResponse
import com.example.stocktick.models.UserProfile
import com.example.stocktick.models.response.FileUploadResponse
import com.example.stocktick.ui.education.ResponseRegisterWebinar
import com.example.stocktick.ui.education.model.BlogItem
import com.example.stocktick.ui.education.model.RegisterWebinarModel
import com.example.stocktick.ui.education.model.WebinarItem
import com.example.stocktick.ui.insurance.InsuranceModel
import com.example.stocktick.ui.loan.LoanFormItem
import com.example.stocktick.ui.loan.LoanItem
import com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models.*
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceInterface {

    //OTP
    @POST("/auth/getotp/")
    suspend fun getOtp(@Body phoneModel: PhoneModel): GetOtpModel

    @POST("/auth/validateotp/")
    suspend fun validateOtp(@Body phoneModel: PhoneModel): GetOtpModel

    //Headlines
    @GET("/get/headlines/")
    suspend fun getHeadline(@Header("page") page: String, @Header("platform") platform: String): Response<HeadlineModel>

    //LOAN
    @GET("/get/loans/")
    suspend fun getLoans(@Header("authToken") authToken: String, @Header("platform") platform: String): Response<List<LoanItem>>


    @POST("/add/loandetails/")
    suspend fun addLoanDetails(@Header("authToken") authToken: String, @Body loanFormItem: LoanFormItem): Response<GetOtpModel>

    //INSURANCE
    @GET("/get/Insurance/")
    suspend fun getInsurances(@Header("authToken") authToken: String, @Header("platform") platform: String): Response<List<LoanItem>>

    @POST("/add/insurance/details/")
    suspend fun addInsuranceDetails(@Header("authToken") authToken: String, @Body insuranceModel: InsuranceModel): Response<GetOtpModel>

    //EDUCATION
    @GET("/get/education/")
    suspend fun getBlogs(@Header("authToken") authToken: String): Response<List<BlogItem>>

    @GET("/get/webinars/")
    suspend fun getWebinar(@Header("authToken") authToken: String): Response<List<WebinarItem>>

    @POST("/subscribe/webinar/")
    suspend fun postRegisterToWebinar(@Header("authToken") authToken: String, @Body registerWebinar: RegisterWebinarModel): Response<ResponseRegisterWebinar>

    //mutual fund calls
    @GET("/riskfactor/questions/")
    suspend fun getRiskFactorQuestions(): Response<List<QuestionDto>>

    @POST("/riskfactor/response/")
    suspend fun submitResponse(
            @Header("authToken") authToken: String,
            @Body answersDto: AnswersDto
    ): Response<PostUserResponseFeedback>

    @GET("/riskfactor/result/")
    suspend fun getRangeResult(
            @Header("authToken") authToken: String
    ): Response<ResultDto>

    @POST("riskfactor/user/")
    suspend fun postUserProfile(
            @Header("authToken") authToken: String,
            @Body userProfileDto: ProfileDto
    ): Response<PostUserProfileFeedback>

    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q1")
    suspend fun postPage1(
            @Header("authToken") authToken: String,
            @Body page1Dto: Page1Dto
    ): Response<PostResponse>

    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q2")
    suspend fun postPage2(
            @Header("authToken") authToken: String,
            @Body page2Dto: Page2Dto
    ): Response<PostResponse>


    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q3")
    suspend fun postPage3(
            @Header("authToken") authToken: String,
            @Body page3Dto: Page3Dto
    ): Response<PostResponse>

    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q4")
    suspend fun postPage4(
            @Header("authToken") authToken: String,
            @Body page4Dto: Page4Dto
    ): Response<PostResponse>

    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q5")
    suspend fun postPage5(
            @Header("authToken") authToken: String,
            @Body page5Dto: Page5Dto
    ): Response<PostResponse>

    //Financial planning requests
    @POST("/finantialplanning/finnantial-Q6")
    suspend fun postPage6(
            @Header("authToken") authToken: String,
            @Body page6Dto: Page6Dto
    ): Response<PostResponse>

    //user profile
    @GET("/get/userinfo")
    suspend fun getUserInfo(
            @Header("authToken") authToken: String
    ): Response<UserProfileDto>

    // UPDATE USER PROFILE
    @POST("/updateprofile")
    suspend fun updateUserProfile(
            @Header("authToken") authToken: String,
            @Body updateUserProfileDTO: UpdateUserProfileDTO
    ): Response<StandardSuccessResponse>

    // get requests of financial plannings
    @GET("/finantialplanning/finnantial-Q1")
    suspend fun getPage1(
            @Header("authToken") authToken: String
    ): Response<List<Page1GetDto>>

    @GET("/finantialplanning/finnantial-Q2")
    suspend fun getPage2(
            @Header("authToken") authToken: String
    ): Response<List<Page2GetDto>>    //This api is not working

    @GET("/finantialplanning/finnantial-Q3")
    suspend fun getPage3(
            @Header("authToken") authToken: String
    ): Response<Page3Dto>

    @GET("/finantialplanning/finnantial-Q4")
    suspend fun getPage4(
            @Header("authToken") authToken: String
    ): Response<Page4Dto>


    @GET("/finantialplanning/finnantial-Q5")
    suspend fun getPage5(
            @Header("authToken") authToken: String
    ): Response<List<Data>>


    @GET("/finantialplanning/finnantial-Q6")
    suspend fun getPage6(
            @Header("authToken") authToken: String
    ): Response<List<String>>

    @GET("/get/userinfo")
    suspend fun getUserDetails(
            @Header("authToken") authToken: String
    ): Response<UserProfile>

    @PUT("/update/userinfo/")
    suspend fun updateUserInfo(
        @Header("authToken") authToken: String,
        @Body profile: UpdateUserProfileDTO
    ) : Response<StandardSuccessResponse>

    @Multipart
    @POST("/fileUploader")
    suspend fun uploadFile(
        @Header("authToken") authToken: String,
        @Part file : MultipartBody.Part
    ) : Response<FileUploadResponse>



}
//JSON STRUCTURE AT https://codeshare.io/wn39rK for education pages
