package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class AifModel(
    val amc_name : String = "",
    val pms_aif_name : String = "",
    val invested_value : String = "",
    val current_amount : String = "",
    val account_number : String = "",
    val invested_date : String = "",
    val return_absolute : String = "",
    val return_cagr : String = "",
    val nominee_name : String = "",
    val relationship : String = "",
    val allocation_percent : String = "",
    val nominee_name2_ : String = "",
    val relationship2 : String = "",
    val allocation_percent2 : String = "",
)
