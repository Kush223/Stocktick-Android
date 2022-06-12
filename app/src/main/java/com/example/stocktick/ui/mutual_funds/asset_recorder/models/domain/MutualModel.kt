package com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain

data class MutualModel(
    var scheme_name : String = "",
    var purchase_amount : String = "",
    var purchase_nav : String = "",
    var current_nav : String = "",
    var current_amount : String = "",
    var div_amount : String = "",
    var gain_short_term : String = "",
    var gain_long_term : String = "",
    var return_absolute : String = "",
    var return_cagr : String = "",
    var nominee_name : String = "",
    var relationship : String = "",
    var allocation_percent : String = "",
    var nominee_name2_ : String = "",
    var relationship2 : String = "",
    var allocation_percent2 : String = "",
    )
