package com.example.stocktick.auth.model


data class GetOtpModel (
    val message: String ?= null,
    val authToken : String? = null,
    val old_user : Boolean ?= null
)

