package com.mihodihasan.marvelsuperheroes.data.source

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.Constants

interface AppDao {
    /*@Query("SELECT * FROM ".plus(Constants.TABLE_NAME_COMICS))
    suspend fun getComicsResultList(): List<ComicsResult?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setComicsResultList(comicsResultList: List<ComicsResult?>?)

    @Query("SELECT * FROM ".plus(Constants.TABLE_NAME_HEROES))
    suspend fun getHeroResultList(): List<HeroResult?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setHeroResultList(heroResultList: List<HeroResult?>?)*/
}
