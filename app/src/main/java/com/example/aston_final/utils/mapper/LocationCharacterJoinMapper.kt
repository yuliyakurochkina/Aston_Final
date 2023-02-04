package com.example.aston_final.utils.mapper

import com.example.aston_final.model.Location
import com.example.aston_final.model.database.LocationCharacterJoin

class LocationCharacterJoinMapper :
    Mapper<MutableList<Location>, MutableList<LocationCharacterJoin>> {
    override fun transform(data: MutableList<Location>): MutableList<LocationCharacterJoin> {
        val listToDb: MutableList<LocationCharacterJoin> = mutableListOf()
        for (location in data) {
            for (i in 0 until location.residents?.size!!) {
                val baseUrl = "https://rickandmortyapi.com/api/character/"
                val characterId = location.residents[i].substring(baseUrl.length).toInt()
                val ceJoin = LocationCharacterJoin(location.id!!, characterId)
                listToDb.add(ceJoin)
            }
        }
        return listToDb
    }
}