package com.mihodihasan.marvelsuperheroes.data.source

class Repository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : DataSource {
    override fun getHeroes(pageNo: Int, callback: DataSource.LoadHeroesCallback) {
        callback.onHeroesLoaded(remoteDataSource.getHeroes())
    }

    override fun getComics(heroId: String, pageNo: Int, callback: DataSource.LoadComicsCallback) {
        callback.onComicsLoaded(localDataSource.getComics())
    }
}