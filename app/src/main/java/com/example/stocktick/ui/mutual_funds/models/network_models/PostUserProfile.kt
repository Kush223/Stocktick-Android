package com.example.stocktick.ui.mutual_funds.models.network_models

data class PostUserProfile(
    val age: Int = 0,
    val email: String = "",
    val gender: String = "",
    val name: String = ""
)

data class PostUserProfileFeedback(
    val message: String
)