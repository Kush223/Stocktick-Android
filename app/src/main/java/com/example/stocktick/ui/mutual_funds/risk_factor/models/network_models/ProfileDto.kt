package com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models

data class ProfileDto(
    val age: Int = 0,
    val email: String = "",
    val gender: String = "",
    val name: String = ""
)

data class PostUserProfileFeedback(
    val message: String
)