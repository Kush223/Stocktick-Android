package com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.network_models

data class GetCategories(
    val id : Int? = 0,
    val created_by: String? = "",
    val created_at: String? = "",
    val modified_by: String? = "",
    val modified_at: String? = "",
    val catg_name: String? = "",
    val icon: String? = "",
    val debt: Int? = 0,
    val equity: Int? = 0,
    val status: String? = "",
    val lock_status: String? = ""
)
