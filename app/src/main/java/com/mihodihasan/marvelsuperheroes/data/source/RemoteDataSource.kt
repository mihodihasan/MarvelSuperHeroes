package com.mihodihasan.marvelsuperheroes.data.source

import android.util.Log
import com.mihodihasan.marvelsuperheroes.common.ResultData
import com.mihodihasan.marvelsuperheroes.data.exception.RemoteDataNotFoundException
import com.mihodihasan.marvelsuperheroes.di.IoDispatcher
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.network.ApiInterface
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apiInterface: ApiInterface, @IoDispatcher private val ioDispatcher: CoroutineDispatcher) {
    suspend fun getHeroes():ResultData<MutableList<Hero>>{
        val result = withContext(ioDispatcher) {
            try {
                val responseBody =
                    apiInterface.getHeroes("name","25","0","9fd2b96ccdc0acf33d90224c98207a49","7370018f941e9085eef964f79587d688","1599721588","")
                val jsonString = responseBody.string()
                Log.d("LSN_TAG", "getHeroes: ".plus(jsonString))
                ResultData.Success(mutableListOf<Hero>())
            } catch (exception: Exception) {
                ResultData.Error(exception)
            }
        }
        return when (result) {
            is ResultData.Success -> {
                val response = result.data
//                withContext(ioDispatcher) { appDao.setListCountries(response) }
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }
    fun getComics():MutableList<Comics>{
        return mutableListOf()
    }
}