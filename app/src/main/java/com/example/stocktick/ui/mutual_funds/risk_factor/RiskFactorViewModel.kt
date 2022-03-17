package com.example.stocktick.ui.mutual_funds.risk_factor

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment.Question
import kotlinx.coroutines.launch

private const val TAG = "RiskFactorViewModel"
class RiskFactorViewModel(application: Application) : AndroidViewModel(application) {

    private val _questions: MutableLiveData<List<Question>> = MutableLiveData()
    val mQuestions : LiveData<List<Question>>
        get() = _questions

    init {
        Log.d(TAG, "inint is called: ")
        getAllQuestions()
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

                var i = 1;
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
                Log.e(TAG, "getAllQuestions: Error :${e.localizedMessage}", )
            }
        }
    }

}