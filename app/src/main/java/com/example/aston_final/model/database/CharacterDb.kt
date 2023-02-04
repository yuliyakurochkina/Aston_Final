package com.example.aston_final.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_full_info")
data class CharacterDb(
    @PrimaryKey val id: Int? = null,
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val type: String? = "",
    val gender: String? = "",
    val origin_name: String? = "",
    val origin_url: String? = "",
    val location_name: String? = "",
    val location_url: String? = "",
    val image: String? = "",
    val url: String? = "",
    val created: String? = ""
)

data class CharacterForListDb(
    val id: Int? = null,
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val gender: String? = "",
    val image: String? = ""
)