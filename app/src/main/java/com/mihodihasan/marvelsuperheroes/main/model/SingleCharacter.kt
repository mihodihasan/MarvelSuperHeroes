package com.mihodihasan.marvelsuperheroes.main.model

import timber.log.Timber

class SingleCharacter (
    private val resourceURI:String?,
    var charId:String?
){
    init {
        val arr = resourceURI?.split("/")
            charId = arr?.get(arr.size-1)
        Timber.d("CHARACTER_ID %s", charId)
    }
}
