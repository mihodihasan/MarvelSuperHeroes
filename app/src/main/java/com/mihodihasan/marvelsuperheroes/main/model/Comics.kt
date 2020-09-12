package com.mihodihasan.marvelsuperheroes.main.model

import com.squareup.moshi.Json

data class Comics(
    val data: ComicsData,
    val code: Int,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    @Json(name = "etag") val eTag: String
)