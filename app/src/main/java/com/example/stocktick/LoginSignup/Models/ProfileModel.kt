package com.example.stocktick.LoginSignup.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @Expose
    @SerializedName("username")
    val username: String? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("gender")
    val gender: String? = null,

    @Expose
    @SerializedName("age")
    val age: Int? = null,

    @Expose
    @SerializedName("email")
    val email: String? = null,

    @Expose
    @SerializedName("profile_url")
    val profile_url: String? = null
)
