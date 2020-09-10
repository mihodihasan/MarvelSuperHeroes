package com.mihodihasan.marvelsuperheroes.main.model

data class ComicsData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: MutableList<ComicsResult>
)