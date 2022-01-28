package com.example.stocktick.ui.loan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoanItem(
        @Expose
        @SerializedName("link")
        val link: String?= null,

        @Expose
        @SerializedName("short_desc")
        val short_desc: String?= null,

        @Expose
        @SerializedName("long_desc")
        val long_desc: String?= null,

        @Expose
        @SerializedName("image_urls")
        val image_url: String?= null,

        @Expose
        @SerializedName("category")
        val category: String?= null,

        @Expose
        @SerializedName("interest")
        val interest: String?= null
)
