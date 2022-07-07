package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

data class AssetData(
    val data :  List<Asset>
)

data class Asset(
    val year : String,
    val asset : Long,
    val liability : Long,
    val net_worth : Long
)
