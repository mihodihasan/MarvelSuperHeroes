package com.mihodihasan.marvelsuperheroes.network

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
    ) : ResponseBody
}
