package com.mihodihasan.marvelsuperheroes.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mihodihasan.marvelsuperheroes.main.model.*
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
    @TypeConverter
    fun stringFromComicsImage(comicsThumbnail: ComicsImage?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getComicsImageFromString(jsonString: String?): ComicsImage? {
        val listType: Type = object : TypeToken<ComicsImage?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromComicsImageList(comicsThumbnail: List<ComicsImage?>?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getComicsImageListFromString(jsonString: String?): List<ComicsImage?>? {
        val listType: Type = object : TypeToken<List<ComicsImage?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromCharacters(comicsThumbnail: Characters?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getCharactersFromString(jsonString: String?): Characters? {
        val listType: Type = object : TypeToken<Characters?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringFromSingleCharacter(comicsThumbnail: SingleCharacter?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getSingleCharacterFromString(jsonString: String?): SingleCharacter? {
        val listType: Type = object : TypeToken<SingleCharacter?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

    @TypeConverter
    fun stringSingleCharacterList(comicsThumbnail: List<SingleCharacter?>?): String? {
        return Gson().toJson(comicsThumbnail)
    }

    @TypeConverter
    fun getSingleCharacterListFromString(jsonString: String?): List<SingleCharacter?>? {
        val listType: Type = object : TypeToken<List<SingleCharacter?>?>() {}.type
        return Gson().fromJson(jsonString, listType)
    }


}
