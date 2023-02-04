package com.example.aston_final.model.dto

data class LocationDto(
    val id: Int = 0,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val residents: String = ""
)

data class LocationForListDto(
    val id: Int? = null,
    val name: String? = "",
    val type: String? = "",
    val dimension: String? = ""
)