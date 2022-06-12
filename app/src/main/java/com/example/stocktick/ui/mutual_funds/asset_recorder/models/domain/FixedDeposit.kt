package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class FixedDeposit(
    var bank_name: String="",
    var branch_addr: String="",
    var deposit_holders_name: String="",
    var amount_invested: String="",
    var investment_date: String="",
    var investment_duration: String="",
    var rate_of_interest: String="",
    var maturity_date: String="",
    var maturity_amount: String="",
    var nominee_name: String="",
    var relationship: String="",
    var allocation: String="",
    var nominee_name2: String="",
    var relationship2: String="",
    var allocation2: String="",
)
