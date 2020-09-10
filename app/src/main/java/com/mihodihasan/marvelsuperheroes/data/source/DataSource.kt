package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero

interface DataSource {
    interface LoadHeroesCallback {
        fun onHeroesLoaded(heroes: MutableList<Hero>)
        fun onDataNotAvailable()
    }

    interface LoadComicsCallback {
        fun onComicsLoaded(comics: MutableList<Comics>)
        fun onDataNotAvailable()
    }

    suspend fun getHeroes(pageNo: Int, callback: LoadHeroesCallback)

    fun getComics(heroId: String, pageNo: Int, callback: LoadComicsCallback)
}
