package com.mihodihasan.marvelsuperheroes.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mihodihasan.marvelsuperheroes.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME_HEROES)
data class HeroResult(
    @PrimaryKey val id: Int,
    val name: String/*,
    val thumbnail: HeroThumbnail?*/
): Serializable
