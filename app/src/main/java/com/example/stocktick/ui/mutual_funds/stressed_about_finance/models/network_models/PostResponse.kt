package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

import com.squareup.moshi.Json

data class PostResponse(
        @Json(name = "message")
        val message: String =""
)
