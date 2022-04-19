package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

import com.squareup.moshi.Json

data class Page2Dto(
    @Json(name = "emi_paid")
    val emi_paid: Int=0,
    @Json(name = "household_expns")
    val household_expns: Int=0,
    @Json(name = "invst_amount")
    val invst_amount: Int=0,
    @Json(name = "lifestyle_expns")
    val lifestyle_expns: Int=0,
    @Json(name = "surplus")
    val surplus: Int=0,
    @Json(name = "tax_paid")
    val tax_paid: Int=0
)