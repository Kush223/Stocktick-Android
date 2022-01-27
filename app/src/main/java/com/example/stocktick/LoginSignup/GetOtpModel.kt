package com.example.stocktick.LoginSignup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetOtpModel (
    @Expose
    @SerializedName("message")
    val message: String ?= null,

    @Expose
    @SerializedName("authToken")
    val authToken : String? = null,

    @Expose
    @SerializedName("Old_User")
    val old_user : Boolean ?= null
)

