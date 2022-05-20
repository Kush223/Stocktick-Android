package com.example.stocktick.models.response

data class FileUploadResponse(
    val id: Int? = 0,
    val created_by : String? = "",
    val file : String? = ""
)
