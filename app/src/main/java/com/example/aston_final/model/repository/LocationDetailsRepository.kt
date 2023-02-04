package com.example.aston_final.model.repository

import com.example.aston_final.model.Character
import com.example.aston_final.model.database.CharacterForListDb
import com.example.aston_final.model.database.ItemsDatabase
import com.example.aston_final.model.dto.LocationDto
import com.example.aston_final.model.retrofit.ApiState
import com.example.aston_final.model.retrofit.RetrofitServices
import com.example.aston_final.utils.mapper.CharacterEpisodeJoinMapper
import com.example.aston_final.utils.mapper.CharacterToDbMapper
import com.example.aston_final.utils.mapper.LocationDbMapper
import com.example.aston_final.utils.mapper.LocationMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationDetailsRepository(
    private val mService: RetrofitServices,
    private val database: ItemsDatabase,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getLocation(locationId: Int): Flow<ApiState<LocationDto>> {
        return flow {
            val location = LocationMapper().transform(mService.getOneLocation(locationId))
            emit(ApiState.success(location))
        }.flowOn(dispatcher)
    }

    suspend fun getCharacterList(charactersId: String): Flow<ApiState<MutableList<Character>>> {
        return flow {
            val characters = mService.getSeveralCharacters(charactersId)
            emit(ApiState.success(characters))
        }.flowOn(dispatcher)
    }

    suspend fun getLocationDb(locationId: Int): Flow<ApiState<LocationDto>> {
        return flow {
            val array: Array<Int> =
                database.getLocationCharacterJoinDao().getCharactersIdForLocation(locationId)
            val location =
                LocationDbMapper(array).transform(database.getLocationDao().getOneById(locationId))
            emit(ApiState.success(location))
        }.flowOn(dispatcher)
    }

    suspend fun getCharacterListDb(locationId: Int): Flow<ApiState<MutableList<CharacterForListDb>>> {
        return flow {
            val charactersArray =
                database.getLocationCharacterJoinDao().getCharactersIdForLocation(locationId)
            val characters = mutableListOf<CharacterForListDb>()
            for (i in charactersArray.indices) {
                characters.add(database.getCharacterDao().getOneForListById(charactersArray[i]))
            }
            emit(ApiState.success(characters))
        }.flowOn(dispatcher)
    }

    suspend fun saveInDb(characterList: MutableList<Character>) {
        database.getCharacterDao().insertAll(CharacterToDbMapper().transform(characterList))
        val listOfCharacterToEpisodes = CharacterEpisodeJoinMapper().transform(characterList)
        database.getCharacterEpisodeJoinDao().insertAll(listOfCharacterToEpisodes)
    }
}