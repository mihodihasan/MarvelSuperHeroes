package com.mihodihasan.marvelsuperheroes.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.Constants

@Dao
interface AppDao {
    @Query("SELECT * FROM TABLE_NAME_COMICS WHERE id = :heroId")
    suspend fun getComicsResultList(heroId:String): List<ComicsResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveComicsListInLocalDb(comicsResultList: List<ComicsResult?>?)

    @Query("SELECT * FROM ".plus(Constants.TABLE_NAME_HEROES))
    suspend fun getHeroResultList(): List<HeroResult>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveHeroListInLocalDb(heroResultList: List<HeroResult?>?)
}
