package com.mihodihasan.marvelsuperheroes.main.model

import java.io.Serializable

data class HeroThumbnail(
    val path: String,
    val extension: String
) : Serializable
