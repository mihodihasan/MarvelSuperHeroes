package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.common.ResultData
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : DataSource {
    override suspend fun getHeroes(pageNo: Int, callback: DataSource.LoadHeroesCallback) {
        try {
            when (val response = remoteDataSource.getHeroes(pageNo)) {
                is ResultData.Success -> {
                    callback.onHeroesLoaded(response.data)
                }
                is ResultData.Error -> {
                    callback.onDataNotAvailable()
                }
            }
        } catch (exception:Exception){
            callback.onDataNotAvailable()
        }
    }

    override fun getComics(heroId: String, pageNo: Int, callback: DataSource.LoadComicsCallback) {
        callback.onComicsLoaded(localDataSource.getComics())
    }
}