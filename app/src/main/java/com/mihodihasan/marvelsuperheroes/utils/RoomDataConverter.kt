package com.mihodihasan.marvelsuperheroes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.ComicsThumbnail
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroThumbnail
import java.io.Serializable
import java.lang.reflect.Type

class RoomDataConverter : Serializable {

    @TypeConverter
    fun stringFromObject(heroResult: HeroResult?): String? {
        return Gson().toJson(heroResult)
    }

    @TypeConverter
    fun getObjectFromString(jsonString: String?): HeroResult? {
        val listType: Type = object : TypeToken<HeroResult?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromList(heroResultList: List<HeroResult?>?): String? {
        return Gson().toJson(heroResultList)
    }

    @TypeConverter
    fun getListFromString(jsonString: String?): List<HeroResult?>? {
        val listType: Type = object : TypeToken<List<HeroResult?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


    @TypeConverter
    fun stringFromComics(comicsResult: ComicsResult?): String? {
        return Gson().toJson(comicsResult)
    }

    @TypeConverter
    fun getComicsFromString(jsonString: String?): ComicsResult? {
        val listType: Type = object : TypeToken<ComicsResult?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromComicsResultList(comicsResultList: List<ComicsResult?>?): String? {
        return Gson().toJson(comicsResultList)
    }

    @TypeConverter
    fun getComicsResultListFromString(jsonString: String?): List<ComicsResult?>? {
        val listType: Type = object : TypeToken<List<ComicsResult?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


    @TypeConverter
    fun stringFromHeroThumbnail(heroThumbnail: HeroThumbnail?): String? {
        return Gson().toJson(heroThumbnail)
    }

    @TypeConverter
    fun getHeroThumbnailFromString(jsonString: String?): HeroThumbnail? {
        val listType: Type = object : TypeToken<HeroThumbnail?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromHeroThumbnailList(comicsResultList: List<HeroThumbnail?>?): String? {
        return Gson().toJson(comicsResultList)
    }

    @TypeConverter
    fun getHeroThumbnailListFromString(jsonString: String?): List<HeroThumbnail?>? {
        val listType: Type = object : TypeToken<List<HeroThumbnail?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


    @TypeConverter
    fun stringFromComicsThumbnail(comicsThumbnail: ComicsThumbnail?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getComicsThumbnailFromString(jsonString: String?): ComicsThumbnail? {
        val listType: Type = object : TypeToken<ComicsThumbnail?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromComicsThumbnailList(comicsThumbnail: List<ComicsThumbnail?>?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getComicsThumbnailListFromString(jsonString: String?): List<ComicsThumbnail?>? {
        val listType: Type = object : TypeToken<List<ComicsThumbnail?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


}
