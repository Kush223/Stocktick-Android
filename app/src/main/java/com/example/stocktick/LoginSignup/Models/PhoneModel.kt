package com.example.stocktick.LoginSignup.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhoneModel (
        @Expose
        @SerializedName("username")
        val phone : String ?= null,

        @Expose
        @SerializedName("otp")
        val otp : String ?= null
)

