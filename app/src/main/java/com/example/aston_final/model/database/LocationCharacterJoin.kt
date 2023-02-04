package com.example.aston_final.model.database

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "location_character_join",
    primaryKeys = ["locationId", "characterId"],
    foreignKeys = [
        ForeignKey(
            entity = LocationDb::class,
            parentColumns = ["id"],
            childColumns = ["locationId"]
        )
    ]
)
data class LocationCharacterJoin(
    val locationId: Int,
    val characterId: Int
)