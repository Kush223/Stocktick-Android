package com.example.stocktick.ui.education.model

import androidx.annotation.Nullable

data class BlogItem(
    var short_desc: String? = null,
    var long_desc: String? = null,
    var image_url: String? = null,
    var video_link: String? = null,
    var blog_link: String? = null,
    var view_type: Int? = 0
)