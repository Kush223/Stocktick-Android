package com.example.stocktick.models.requests

data class UpdateUserProfileDTO(
        val age: Int?=-1,
        val email: String?="",
        val gender: String?="",
        val name: String?="",
        val profile_url: String?=""
)
