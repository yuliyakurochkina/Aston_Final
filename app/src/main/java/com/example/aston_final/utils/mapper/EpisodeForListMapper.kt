package com.example.aston_final.utils.mapper

import com.example.aston_final.model.Episode
import com.example.aston_final.model.database.EpisodeForListDb
import com.example.aston_final.model.dto.EpisodeForListDto

class EpisodeForListMapper : Mapper<List<Episode>, MutableList<EpisodeForListDto>> {
    override fun transform(data: List<Episode>): MutableList<EpisodeForListDto> {
        val newMutableList = mutableListOf<EpisodeForListDto>()
        for (episode in data) {
            newMutableList.add(
                EpisodeForListDto(
                    id = episode.id,
                    name = episode.name,
                    air_date = episode.air_date,
                    episode = episode.episode
                )
            )
        }
        return newMutableList
    }
}

class EpisodeForListDbMapper : Mapper<List<EpisodeForListDb>, MutableList<EpisodeForListDto>> {
    override fun transform(data: List<EpisodeForListDb>): MutableList<EpisodeForListDto> {
        val newMutableList = mutableListOf<EpisodeForListDto>()
        for (episode in data) {
            newMutableList.add(
                EpisodeForListDto(
                    id = episode.id,
                    name = episode.name,
                    air_date = episode.air_date,
                    episode = episode.episode
                )
            )
        }
        return newMutableList
    }
}