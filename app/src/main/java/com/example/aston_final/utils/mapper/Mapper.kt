package com.example.aston_final.utils.mapper

interface Mapper<SRC, DTO> {
    fun transform(data: SRC): DTO
}