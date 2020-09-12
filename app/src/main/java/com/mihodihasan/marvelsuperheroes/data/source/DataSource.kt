package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult

interface DataSource {
    interface LoadHeroesCallback {
        fun onHeroesLoaded(heroes: MutableList<HeroResult>)
        fun onDataNotAvailable(message:String)
    }

    interface LoadComicsCallback {
        fun onComicsLoaded(comics: MutableList<ComicsResult>)
        fun onDataNotAvailable(message:String)
    }

    suspend fun getHeroes(pageNo: Int, callback: LoadHeroesCallback)

    suspend fun getComics(heroId: String, pageNo: Int, callback: LoadComicsCallback)
}
