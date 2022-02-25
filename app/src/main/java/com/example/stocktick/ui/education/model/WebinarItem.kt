package com.example.stocktick.ui.education.model

import com.squareup.moshi.Json

data class WebinarItem(
        var title: String?=null,
        var short_desc: String?=null,
        @Json(name = "long_desc")
        var image_uri: String?=null,
        var hosted_by: String?=null,
        var other_host_name: String?=null,
        var webinar_redirect_url: String?=null
)