package com.example.aston_final.utils.mapper

import com.example.aston_final.model.Character
import com.example.aston_final.model.database.CharacterForListDb
import com.example.aston_final.model.dto.CharacterForListDto

class CharacterForListMapper : Mapper<List<Character>, MutableList<CharacterForListDto>> {
    override fun transform(data: List<Character>): MutableList<CharacterForListDto> {
        val newMutableList = mutableListOf<CharacterForListDto>()
        for (character in data) {
            newMutableList.add(
                CharacterForListDto(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    image = character.image
                )
            )
        }
        return newMutableList
    }
}

class CharacterForListDbMapper :
    Mapper<List<CharacterForListDb>, MutableList<CharacterForListDto>> {
    override fun transform(data: List<CharacterForListDb>): MutableList<CharacterForListDto> {
        val newMutableList = mutableListOf<CharacterForListDto>()
        for (character in data) {
            newMutableList.add(
                CharacterForListDto(
                    id = character.id,
                    name = character.name,
                    status = character.status,
                    species = character.species,
                    gender = character.gender,
                    image = character.image
                )
            )
        }
        return newMutableList
    }
}