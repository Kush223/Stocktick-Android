package com.example.stocktick.ui.mutual_funds.risk_factor.models.network_models

data class ResultDto(
    val created_at: String? = "",
    val created_by: Any? = Any(),
    val description: String = "",
    val id: Int? = 0,
    val modified_at: String? = "",
    val modified_by: Any? = Any(),
    val risk_profile: String = "",
    val value1: Int? = 0,
    val value2: Int? = 0
)