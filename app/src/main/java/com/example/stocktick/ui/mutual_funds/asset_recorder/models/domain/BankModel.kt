package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class BankModel(
    var id : String = "0",
    var bank_name : String = "",
    var branch_address : String = "",
    var account_holder_name : String = "",
    var last_4_digit_of_account_number : String = "",
    var account_type : String = "",
    var nominee_name : String = "",
    var relationship : String = "",
    var allocation_percent : String = "",
    var nominee_name2_ : String = "",
    var relationship2 : String = "",
    var allocation_percent2 : String = "",
    )
