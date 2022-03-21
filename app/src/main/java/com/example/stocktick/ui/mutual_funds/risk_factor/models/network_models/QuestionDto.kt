package com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models

data class QuestionDto(
    val created_by: String?,
    val id: Int?,
    val modified_by: String?,
    val op1: String? = "",
    val op2: String? = "",
    val op3: String? = "",
    val op4: String? = "",
    val op5: String? = "",
    val op6: String? = "",
    val questions: String = ""

)