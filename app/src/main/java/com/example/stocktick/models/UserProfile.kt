package com.example.stocktick.models

data class UserProfile(
    val name: String?,
    val gender: String?,
    val age: Int=0,
    val phone: String?,
    val email: String?,
    val profile_url: String?,
)
