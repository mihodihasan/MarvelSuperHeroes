package com.mihodihasan.marvelsuperheroes.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mihodihasan.marvelsuperheroes.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME_COMICS)
data class ComicsResult (
    @PrimaryKey val id : Int,
    val title: String,
    val modified: String,
    val description: String/*,
    val thumbnail: ComicsThumbnail?,
    val images: List<ComicsImage?>*/
) : Serializable
