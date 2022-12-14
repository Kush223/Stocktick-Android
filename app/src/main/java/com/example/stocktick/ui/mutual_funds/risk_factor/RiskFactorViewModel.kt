package com.example.stocktick.ui.mutual_funds.risk_factor

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.customviews.PerformanceLabel
import com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment.Question
import com.example.stocktick.ui.mutual_funds.risk_factor.models.domain_models.Result
import com.example.stocktick.ui.mutual_funds.risk_factor.models.domain_models.UserProfile
import com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models.AnswersDto
import com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models.ProfileDto
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "RiskFactorViewModel"
class RiskFactorViewModel(application: Application) : AndroidViewModel(application) {

    private var tokenSharedPreference: String

    private val _questions: MutableLiveData<List<Question>> = MutableLiveData()
    val mQuestions : LiveData<List<Question>>
        get() = _questions

    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
    }

    fun getDownloadUrl( result : (isSuccessful : Boolean, url : String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getRiskFactorPdf(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && response.body()?.get("url") != null){
                    withContext(Dispatchers.Main){
                        result(true, response.body()?.get("url")!!)
                    }
                }
                else withContext(Dispatchers.Main){
                    Log.d(TAG, "downloadPdf: Error : ${response.body()}")
                    result(false, "")
                }
            } catch (e : Exception){
                Log.d(TAG, "downloadPdf: Error :${e.localizedMessage}")
                withContext(Dispatchers.Main){
                    result(false, "")
                }
            }
        }
    }


    fun getAllQuestions(){
        viewModelScope.launch {
            try {
                val response = RetrofitClientInstance.retrofitService.getRiskFactorQuestions()
                if (!response.isSuccessful){
                    Log.d(TAG, "getAllQuestions: Response unsuccessful")
                    return@launch
                }
                val body = response.body() ?: return@launch

                val tempQuestions = mutableListOf<Question>()

                var i = 1
                for( question in body){
                    val options = listOf(
                        question.op1,
                        question.op2,
                        question.op3,
                        question.op4,
                        question.op5,
                        question.op6
                    )
                    tempQuestions.add(
                        Question(
                            question = question.questions,
                            options.filter {
                                it!= null && it.isNotEmpty()
                            }.map {
                                  it ?: ""
                            },
                            i++
                        )
                    )
                }

                _questions.value = tempQuestions
                Log.d(TAG, "getAllQuestions: $tempQuestions")


            }
            catch (e: Exception){
                Log.e(TAG, "getAllQuestions: Error :${e.localizedMessage}" )
            }
        }
    }

    fun postUserResponse(
        answersDto: AnswersDto,
        onResponse: (isSuccessful: Boolean) -> Unit
    ) {
        Log.d(TAG, "postUserResponse: postUserResponse :$answersDto , token :$tokenSharedPreference")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.submitResponse(
                        tokenSharedPreference,
                        answersDto
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

    fun postUserProfile(
        age: Int,
        email: String,
        gender: String,
        name: String,
        onResponse: (isSuccessful: Boolean)-> Unit
    ){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClientInstance.retrofitService.postUserProfile(
                    authToken = tokenSharedPreference,
                    userProfileDto = ProfileDto(
                        age = age,
                        email = email,
                        gender = gender,
                        name = name
                    )
                )
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        Log.d(TAG, "postUserProfile: ${response.body()}")
                        onResponse(true)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResponse(false)
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onResponse(false)
                }
            }
        }
    }

    fun getRangeResult(onResponse: (
        isSuccessful: Boolean,
        result: Result?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getRangeResult(
                    authToken = tokenSharedPreference
                )
                Log.d(TAG, "getRangeResult: ${response.body()}")
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            Result(
                                category = when (response.body()?.range?.get(0)?.risk_profile) {
                                    "Wealth Steady" -> PerformanceLabel.WEALTH_STEADY
                                    "Wealth Protect" -> PerformanceLabel.WEALTH_PROTECT
                                    "Wealth Conserve" -> PerformanceLabel.WEALTH_CONSERVE
                                    "Wealth Build" -> PerformanceLabel.WEALTH_BUILD
                                    "Wealth Grow" -> PerformanceLabel.WEALTH_GROW
                                    "Wealth Multiply" -> PerformanceLabel.WEALTH_MULTIPLY
                                    else -> PerformanceLabel.WEALTH_BUILD
                                },
                                description = response.body()?.range?.get(0)?.description
                                    ?: "No description found",
                                score = (response.body()?.totalScore ?: "80").toString()
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
                Log.e(TAG, "getRangeResult: ${e.localizedMessage}" )
                withContext(Dispatchers.Main) {
                    onResponse(
                        false,
                        null
                    )
                }
            }

        }



    }

    fun getUserProfile(onResponse: (
        isSuccessful: Boolean,
        userProfile: UserProfile?
    ) -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getUserInfo(
                    authToken = tokenSharedPreference
                )
                Log.d(TAG, "getRangeResult: ${response.body()}")
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        onResponse(
                            true,
                            UserProfile(
                                name = response.body()?.name,
                                email = response.body()?.email,
                                gender = when (response.body()?.gender) {
                                    "M"->{
                                        "Male"}

                                        "F"-> {
                                            "Female"
                                        }
                                    null -> null
                                    else -> null

                                },
                                age = response.body()?.age
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
                Log.e(TAG, "getRangeResult: ${e.localizedMessage}" )
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