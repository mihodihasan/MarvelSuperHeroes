package com.mihodihasan.marvelsuperheroes.main.model

data class ComicsResult (
    var id : Int,
    var title: String,
    var modified: String,
    var description: String?,
    var thumbnail: ComicsThumbnail,
    var images: List<ComicsImage>
)
