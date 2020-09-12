package com.mihodihasan.marvelsuperheroes.data.source

import com.mihodihasan.marvelsuperheroes.common.ResultData
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.Constants
import com.mihodihasan.marvelsuperheroes.utils.SharedPreferenceManager
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource,
                                     private val sharedPreferenceManager: SharedPreferenceManager
) : DataSource {
    override suspend fun getHeroes(pageNo: Int, callback: DataSource.LoadHeroesCallback) {
        if (sharedPreferenceManager.getInt(Constants.HEROES_API_MAX_PAGE)>=pageNo || getTimeDiffInMillis(Constants.HEROES_API_LAST_HIT) < 1000*60*3 ){
            //fetch from local
            val list= mutableListOf<HeroResult>()
            list.addAll(localDataSource.getHeroes())
            callback.onHeroesLoaded(list)
        } else{
            //fetch from remote
            try {
                when (val response = remoteDataSource.getHeroes(pageNo)) {
                    is ResultData.Success -> {
                        sharedPreferenceManager.saveString(Constants.HEROES_API_LAST_HIT, Date().toString())
                        sharedPreferenceManager.saveInt(Constants.HEROES_API_MAX_PAGE, pageNo)
                        localDataSource.saveHeroes(response.data)
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
    }

    override suspend fun getComics(heroId: String, pageNo: Int, callback: DataSource.LoadComicsCallback) {
        try {
            when (val response = remoteDataSource.getComics(heroId, pageNo)) {
                is ResultData.Success -> {
                    callback.onComicsLoaded(response.data)
                }
                is ResultData.Error -> {
                    callback.onDataNotAvailable()
                }
            }
        } catch (exception:Exception){
            callback.onDataNotAvailable()
        }
    }

    private fun getTimeDiffInMillis(previousTime:String):Long{
        return try {
            val simpleDateFormat:SimpleDateFormat =
                SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT, Locale.ENGLISH)
            val prevDate = simpleDateFormat.parse(previousTime)
            val currentDate= Date()
            if (prevDate!=null) currentDate.time - prevDate.time else 0
        } catch (exception:Exception){
            0
        }
    }
}