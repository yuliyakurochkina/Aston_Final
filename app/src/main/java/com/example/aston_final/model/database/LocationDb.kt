package com.example.aston_final.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aston_final.model.Location

@Entity(tableName = "locations_full_info")
class LocationDb(
    @PrimaryKey
    val id: Int?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val url: String?,
    val created: String?
) {
    companion object {
        private fun locationToDb(location: Location): LocationDb {
            return LocationDb(
                id = location.id,
                name = location.name,
                type = location.type,
                dimension = location.dimension,
                url = location.url,
                created = location.created
            )
        }

        fun locationToDb(locations: MutableList<Location>): MutableList<LocationDb> {
            val newMutableList = mutableListOf<LocationDb>()
            for (location in locations) {
                newMutableList.add(locationToDb(location))
            }
            return newMutableList
        }
    }
}

class LocationForListDb(
    val id: Int?,
    val name: String?,
    val type: String?,
    val dimension: String?
)