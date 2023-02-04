package com.example.aston_final.model.dto

data class EpisodeDto(
    val id: Int = 0,
    val name: String = "",
    val air_date: String = "",
    val episode: String = "",
    val characters: String = ""
)

data class EpisodeForListDto(
    val id: Int? = null,
    val name: String? = "",
    val air_date: String? = "",
    val episode: String? = ""
)