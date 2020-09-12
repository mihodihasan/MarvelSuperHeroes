package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.di.IoDispatcher
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(@IoDispatcher private val ioDispatcher: CoroutineDispatcher, private val appDao: AppDao){

    suspend fun getHeroes():List<HeroResult>{
        return withContext(ioDispatcher){appDao.getHeroResultList()}
    }
    suspend fun getComics(heroId:String):List<ComicsResult>{
        return withContext(ioDispatcher){appDao.getComicsResultList(heroId)}
    }
    suspend fun saveHeroes(heroList: List<HeroResult?>?){
        withContext(ioDispatcher){
            appDao.saveHeroListInLocalDb(heroList)
        }
    }
    suspend fun saveComics(comicsList: List<ComicsResult?>?){
        withContext(ioDispatcher){
            appDao.saveComicsListInLocalDb(comicsList)
        }
    }

}