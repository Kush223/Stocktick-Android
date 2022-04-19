package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

data class Page5Dto(
    val data: List<Data>
)

data class Data(
    val goal: String,
    val priority: String
)