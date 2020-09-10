package com.mihodihasan.marvelsuperheroes.main.model

import com.squareup.moshi.Json

data class HeroData (
    val offset:Int,
    val limit:Int,
    val total:Int,
    val count:Int,
    @Json(name = "results")val heroResults:MutableList<HeroResult>,
)
