package com.mihodihasan.marvelsuperheroes.di

import android.app.Application
import androidx.room.Room
import com.mihodihasan.marvelsuperheroes.data.source.AppDao
import com.mihodihasan.marvelsuperheroes.data.source.MarvelRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): MarvelRoomDB {
        return Room.databaseBuilder(application, MarvelRoomDB::class.java, "marvel_db").build()
    }

    @Singleton
    @Provides
    fun providesAppDao(marvelRoomDB: MarvelRoomDB): AppDao {
        return marvelRoomDB.appDao()
    }
}