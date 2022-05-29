package com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models

data class MfModel(
    val mfName: String="Mutual fund name",
    val iconUrl: String="",
    val oneDayR: String="00.00%",
    val oneYearR: String="00.01%",
    val threeYearR: String="00.02%",
    val shortDescription: String="",
    val redirectUrl: String="",
    val lockStatus: String = "UNLOCKED"
)
