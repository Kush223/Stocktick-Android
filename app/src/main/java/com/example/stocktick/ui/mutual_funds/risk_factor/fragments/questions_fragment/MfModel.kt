package com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment

data class MfModel(
    val mfName: String="Mutual fund name",
    val iconUrl: String="",
    val oneDayR: String="00.00%",
    val oneYearR: String="00.00%",
    val threeYearR: String="00.00%",
    val shortDescription: String="",
    val redirectUrl: String=""
)
