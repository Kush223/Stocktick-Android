package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class BaModel(
    val bank_name : String = "",
    val branch_address : String = "",
    val account_holder_name : String = "",
    val last_4_digit_of_account_number : String = "",
    val account_type : String = "",
    val nominee_name : String = "",
    val relationship : String = "",
    val allocation_percent : String = "",
    val nominee_name2_ : String = "",
    val relationship2 : String = "",
    val allocation_percent2 : String = "",
    )
