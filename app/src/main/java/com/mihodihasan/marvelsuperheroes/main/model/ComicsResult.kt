package com.mihodihasan.marvelsuperheroes.main.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mihodihasan.marvelsuperheroes.utils.Constants
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = Constants.TABLE_NAME_COMICS)
class ComicsResult (
    @PrimaryKey @NotNull @NonNull val id : Int,
    @NotNull @NonNull val title: String,
    @NotNull @NonNull val modified: String,
    @NotNull @NonNull val description: String,
    val thumbnail: ComicsThumbnail,
    val images: List<ComicsImage?>?
) : Serializable
