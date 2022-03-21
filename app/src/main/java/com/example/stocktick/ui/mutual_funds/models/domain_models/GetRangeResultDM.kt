package com.example.stocktick.ui.mutual_funds.models.domain_models

import com.example.stocktick.ui.customviews.PerformanceLabel

data class GetRangeResultDM (
    val category: PerformanceLabel,
    val description: String,
    val score: String
        )