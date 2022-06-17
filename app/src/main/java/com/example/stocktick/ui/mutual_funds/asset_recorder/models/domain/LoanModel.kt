package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class LoanModel(
    var id : String = "0",
    var bank_name : String = "",
    var loan_account_number : String = "",
    var loan_amount : String = "",
    var asset_name : String = "",
    var asset_type : String = "",
    var loan_start_date : String = "",
    var loan_tenure : String = "",
    var emi_amount : String = "",
    var roi : String = "",
    var ecs_bank : String = "",
    var insurance_protect_loan : String = "",
    var current_outstanding : String = "",
    )
