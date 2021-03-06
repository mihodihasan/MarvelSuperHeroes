package com.mihodihasan.marvelsuperheroes.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult

@Dao
interface AppDao {
    @Query("SELECT * FROM TABLE_NAME_COMICS WHERE characters like :heroId")
    suspend fun getComicsResultList(heroId:String): List<ComicsResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComicsListInLocalDb(comicsResultList: List<ComicsResult?>?)

    @Query("SELECT * FROM TABLE_NAME_HEROES order by name ASC")
    suspend fun getHeroResultList(): List<HeroResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHeroListInLocalDb(heroResultList: List<HeroResult?>?)

    @Query("DELETE from TABLE_NAME_COMICS")
    suspend fun deleteAllComics()

    @Query("DELETE from TABLE_NAME_HEROES")
    suspend fun deleteAllHeroes()


}
