package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero

class RemoteDataSource {
    fun getHeroes():MutableList<Hero>{
        return mutableListOf()
    }
    fun getComics():MutableList<Comics>{
        return mutableListOf()
    }
}