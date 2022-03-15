package com.example.stocktick.ui.mutual_funds.risk_factor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stocktick.ui.mutual_funds.risk_factor.questions_fragment.Question

class RiskFactorViewModel : ViewModel() {

    private val _questions: MutableLiveData<List<Question>> = MutableLiveData()
    val mQuestions : LiveData<List<Question>>
        get() = _questions

    init {
        _questions.value = questions
    }

    companion object {
        val questions = listOf(
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 1
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 2
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 3
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 4
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 5
            ),

            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 6
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 7
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 8
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 9
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 10
            ),

            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 11
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 12
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 13
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 14
            ),
            Question(
                "Who is the prime minister of India", listOf(
                    "Rahul Ghandi",
                    "Amit ",
                    "Narendra Modi",
                    "Mahatma Ghandi"
                ), 15
            )
        )
    }
}