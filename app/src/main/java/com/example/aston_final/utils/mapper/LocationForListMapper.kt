package com.example.aston_final.utils.mapper

import com.example.aston_final.model.Location
import com.example.aston_final.model.database.LocationForListDb
import com.example.aston_final.model.dto.LocationForListDto

class LocationForListMapper : Mapper<Location, LocationForListDto> {
    override fun transform(data: Location): LocationForListDto {
        return LocationForListDto(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension
        )
    }
}

class LocationForListDbMapper : Mapper<LocationForListDb, LocationForListDto> {
    override fun transform(data: LocationForListDb): LocationForListDto {
        return LocationForListDto(
            id = data.id,
            name = data.name,
            type = data.type,
            dimension = data.dimension
        )
    }
}