package com.mihodihasan.marvelsuperheroes.main.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mihodihasan.marvelsuperheroes.utils.Constants
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "TABLE_NAME_HEROES")
data class HeroResult(
    @PrimaryKey @NotNull @NonNull val id: Int,
    val name: String,
    val thumbnail: HeroThumbnail
):Serializable
