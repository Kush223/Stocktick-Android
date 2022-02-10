package com.example.stocktick.auth.model

data class ProfileModel(
        val username: String? = null,
        val name: String? = null,
        val gender: String? = null,
        val age: Int? = null,
        val email: String? = null,
        val profile_url: String? = null
)
