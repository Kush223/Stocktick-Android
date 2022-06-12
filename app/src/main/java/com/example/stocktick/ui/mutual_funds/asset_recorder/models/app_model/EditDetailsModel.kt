package com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model

import android.text.InputType

data class EditDetailsModel(
    val label : String,
    val hint : String,
    val inputType: Int = InputType.TYPE_CLASS_TEXT,
    var text : String = ""
)
