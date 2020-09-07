package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import javax.inject.Inject

class LocalDataSource @Inject constructor(){

    fun getHeroes():MutableList<Hero>{
        return mutableListOf()
    }
    fun getComics():MutableList<Comics>{
        return mutableListOf()
    }

}