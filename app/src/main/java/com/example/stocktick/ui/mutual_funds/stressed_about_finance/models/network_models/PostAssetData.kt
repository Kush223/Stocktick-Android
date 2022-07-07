package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models

data class PostAssetData(
    val data :  List<PostAsset>

)

data class PostAsset(
    val year : String,
    val asset : Long,
    val liability : Long,
    val networth : Long
)

