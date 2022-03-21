package com.example.stocktick.ui.mutual_funds.models.network_models

data class PostUserResponse(
    val option1: String = "",
    val option2: String = "",
    val option3: String = "",
    val option4: String = "",
    val option5: String = "",
    val option6: String = "",
    val option7: String = "",
    val option8: String = "",
    val option9: String = "",
    val option10: String = "",
    )

data class PostUserResponseFeedback(
    val message : String
)