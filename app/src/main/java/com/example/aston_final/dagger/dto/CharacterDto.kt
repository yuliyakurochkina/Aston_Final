package com.example.aston_final.model.dto

data class CharacterDto(
    val id: Int? = null,
    val name: String? = "",
    val status: String? = "",
    val species: String? = "",
    val type: String? = "",
    val gender: String? = "",
    val origin: LocationForCharacterDto? = null,
    val location: LocationForCharacterDto? = null,
    val image: String? = "",
    val episode: String? = ""
)

data class LocationForCharacterDto(
    val name: String?,
    val locationId: Int?
)

data class CharacterForListDto(
    val id: Int?,
    val name: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val image: String?
)