package com.example.aston_final.utils.mapper

import com.example.aston_final.model.Episode
import com.example.aston_final.model.database.EpisodeCharacterJoin

class EpisodeCharacterJoinMapper : Mapper<MutableList<Episode>, MutableList<EpisodeCharacterJoin>> {
    override fun transform(data: MutableList<Episode>): MutableList<EpisodeCharacterJoin> {
        val listToDb: MutableList<EpisodeCharacterJoin> = mutableListOf()
        for (episode in data) {
            for (i in 0 until episode.characters?.size!!) {
                val baseUrl = "https://rickandmortyapi.com/api/character/"
                val characterId = episode.characters[i].substring(baseUrl.length).toInt()
                val ceJoin = EpisodeCharacterJoin(episode.id!!, characterId)
                listToDb.add(ceJoin)
            }
        }
        return listToDb
    }
}