package com.example.stocktick.ui.mutual_funds.risk_factor.models.domain_models

import com.example.stocktick.ui.customviews.PerformanceLabel

data class Result (
    val category: PerformanceLabel,
    val description: String,
    val score: String
        )