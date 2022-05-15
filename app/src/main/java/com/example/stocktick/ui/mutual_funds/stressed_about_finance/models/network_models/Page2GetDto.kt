package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

data class Page2GetDto(
    val created_at: String?,
    val created_by: String?,
    val emi_paid: Int?,
    val household_expns: Int?,
    val id: Int?,
    val invst_amount: Int?,
    val lifestyle_expns: Int?,
    val modified_at: String?,
    val modified_by: String?,
    val status: String?,
    val surplus: Int?,
    val tax_paid: Int?
)