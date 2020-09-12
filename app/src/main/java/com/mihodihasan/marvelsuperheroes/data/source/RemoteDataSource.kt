package com.mihodihasan.marvelsuperheroes.data.source

import android.util.Log
import com.mihodihasan.marvelsuperheroes.common.ResultData
import com.mihodihasan.marvelsuperheroes.data.exception.RemoteDataNotFoundException
import com.mihodihasan.marvelsuperheroes.di.IoDispatcher
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.network.ApiInterface
import com.mihodihasan.marvelsuperheroes.utils.Constants
import com.mihodihasan.marvelsuperheroes.utils.SharedPreferenceManager
import com.mihodihasan.marvelsuperheroes.utils.md5
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getHeroes(pageNo: Int): ResultData<MutableList<HeroResult>> {
        val result = withContext(ioDispatcher) {
            try {
                val offset: Int = pageNo * 25
                val limit: Int = offset + 25
                val timestamp : Long = Date().time
                val decodedHash = "".plus(timestamp).plus("9299d0b0d2660100b5bb18451282d129ca9b2430").plus("9fd2b96ccdc0acf33d90224c98207a49")
                val encodedHash = decodedHash.md5()
                val heroResponse =
                    apiInterface.getHeroes(
                        "name",
                        "".plus(limit),
                        "".plus(offset),
                        "9fd2b96ccdc0acf33d90224c98207a49",
                        "".plus(encodedHash),
                        "".plus(timestamp),
                        ""
                    )
                if (heroResponse.code==200){
                    ResultData.Success(heroResponse.mData.heroResults)
                }else{
                    ResultData.Error(heroResponse.status)
                }

            } catch (exception: Exception) {
                ResultData.Error(exception.toString())
            }
        }
        return when (result) {
            is ResultData.Success -> {
                val response = result.data
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException().toString())
            }
        }
    }

    suspend fun getComics(heroId:String, pageNo: Int): ResultData<MutableList<ComicsResult>> {
        val result = withContext(ioDispatcher) {
            try {
                val offset: Int = pageNo * 25
                val limit: Int = offset + 25
                val timestamp : Long = Date().time
                val decodedHash = "".plus(timestamp).plus("9299d0b0d2660100b5bb18451282d129ca9b2430").plus("9fd2b96ccdc0acf33d90224c98207a49")
                val encodedHash = decodedHash.md5()
                val comicsResponse =
                    apiInterface.getComics(
                        "title",
                        "".plus(limit),
                        "".plus(offset),
                        "9fd2b96ccdc0acf33d90224c98207a49",
                        "".plus(encodedHash),
                        "".plus(timestamp),
                        heroId,
                        ""
                    )
                if (comicsResponse.code==200){
                ResultData.Success(comicsResponse.data.results)
                } else{
                    ResultData.Error(comicsResponse.status)
                }
            } catch (exception: Exception) {
                ResultData.Error(exception.toString())
            }
        }
        return when (result) {
            is ResultData.Success -> {
                val response = result.data
//                withContext(ioDispatcher) { appDao.setListCountries(response) }
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException().toString())
            }
        }
    }
}