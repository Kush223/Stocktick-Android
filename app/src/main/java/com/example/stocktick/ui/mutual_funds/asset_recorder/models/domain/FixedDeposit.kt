package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class FixedDeposit(
    val bank_name: String="",
    val branch_addr: String="",
    val deposit_holders_name: String="",
    val amount_invested: String="",
    val investment_date: String="",
    val investment_duration: String="",
    val rate_of_interest: String="",
    val maturity_date: String="",
    val maturity_amount: String="",
    val nominee_name: String="",
    val relationship: String="",
    val allocation: String="",
    val nominee_name2: String="",
    val relationship2: String="",
    val allocation2: String="",
)
