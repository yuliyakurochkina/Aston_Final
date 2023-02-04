package com.example.aston_final.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_full_info")
data class EpisodeDb(
    @PrimaryKey val id: Int? = null,
    val name: String? = "",
    val air_date: String? = "",
    val episode: String? = "",
    val url: String? = "",
    val created: String? = ""
)

data class EpisodeForListDb(
    val id: Int? = null,
    val name: String? = "",
    val air_date: String? = "",
    val episode: String? = ""
)