package com.mihodihasan.marvelsuperheroes.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.Dummy
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.RoomDataConverter


@Database(entities = [HeroResult::class, ComicsResult::class], version = 1, exportSchema = false)
@TypeConverters(RoomDataConverter::class)
abstract class MarvelRoomDB : RoomDatabase() {
    abstract fun appDao(): AppDao
}