package com.mihodihasan.marvelsuperheroes.network

import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/public/characters")
    suspend fun getHeroes(
        @Query("orderBy") orderBy:String,
        @Query("limit") limit:String,
        @Query("offset") offset:String,
        @Query("apikey") apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") timeStamp:String,
        @Header("If-None-Match") eTag:String
    ) : Hero

    @GET("v1/public/comics")
    suspend fun getComics(
        @Query("orderBy") orderBy:String,
        @Query("limit") limit:String,
        @Query("offset") offset:String,
        @Query("apikey") apiKey:String,
        @Query("hash") hash:String,
        @Query("ts") timeStamp:String,
        @Query("characters") characterId:String,
        @Header("If-None-Match") eTag:String
    ) : Comics

}
