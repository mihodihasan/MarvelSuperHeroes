package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.main.model.HeroResult

interface DataSource {
    interface LoadHeroesCallback {
        fun onHeroesLoaded(heroes: MutableList<HeroResult>)
        fun onDataNotAvailable()
    }

    interface LoadComicsCallback {
        fun onComicsLoaded(comics: MutableList<HeroResult>)
        fun onDataNotAvailable()
    }

    suspend fun getHeroes(pageNo: Int, callback: LoadHeroesCallback)

    suspend fun getComics(heroId: String, pageNo: Int, callback: LoadComicsCallback)
}
