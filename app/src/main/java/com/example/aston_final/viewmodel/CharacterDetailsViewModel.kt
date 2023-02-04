package com.example.aston_final.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aston_final.model.dto.CharacterDto
import com.example.aston_final.model.dto.EpisodeForListDto
import com.example.aston_final.model.dto.LocationForListDto
import com.example.aston_final.model.repository.CharacterDetailsRepository
import com.example.aston_final.model.retrofit.ApiState
import com.example.aston_final.model.retrofit.Status
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.utils.mapper.EpisodeForListDbMapper
import com.example.aston_final.utils.mapper.EpisodeForListMapper
import com.example.aston_final.utils.mapper.LocationForListDbMapper
import com.example.aston_final.utils.mapper.LocationForListMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    characterID: Int,
    val repository: CharacterDetailsRepository,
    internetChecker: InternetConnectionChecker
) : ViewModel() {

    val character = MutableStateFlow(ApiState(Status.LOADING, CharacterDto(), ""))
    val origin = MutableStateFlow(ApiState(Status.LOADING, LocationForListDto(), ""))
    val location = MutableStateFlow(ApiState(Status.LOADING, LocationForListDto(), ""))
    val episodes =
        MutableStateFlow(ApiState(Status.LOADING, mutableListOf<EpisodeForListDto>(), ""))
    private val network: Boolean = internetChecker.isOnline()

    init {
        getCharacter(characterID)
    }

    private fun getCharacter(characterID: Int) {
        character.value = ApiState.loading()
        viewModelScope.launch {
            val gottenCharacter: Flow<ApiState<CharacterDto>> = if (network) {
                repository.getCharacter(characterID)
            } else {
                repository.getCharacterDb(characterID)
            }
            gottenCharacter
                .catch {
                    character.value = ApiState.error(it.message.toString())
                }
                .collect {
                    character.value = ApiState.success(it.data)
                    if (network) {
                        val episodesId = character.value.data?.episode ?: ""
                        getEpisodes(episodesId)
                    } else {
                        val characterId = character.value.data?.id ?: 0
                        getEpisodesDb(characterId)
                    }
                    if (character.value.data?.location?.name != "unknown") {
                        val locationId = character.value.data?.location?.locationId ?: 0
                        if (network) {
                            getLocation(locationId, location)
                        } else {
                            getLocationDb(locationId, location)
                        }
                    } else {
                        location.value = ApiState.success(
                            LocationForListDto(
                                id = -1,
                                name = "unknown",
                                type = "",
                                dimension = ""
                            )
                        )
                    }
                    if (character.value.data?.origin?.name != "unknown") {
                        val originId = character.value.data?.origin?.locationId ?: 0
                        if (network) {
                            getLocation(originId, origin)
                        } else {
                            getLocationDb(originId, origin)
                        }
                    } else {
                        origin.value = ApiState.success(
                            LocationForListDto(
                                id = -1,
                                name = "unknown",
                                type = "",
                                dimension = ""
                            )
                        )
                    }
                }
        }
    }

    private suspend fun getEpisodes(needIds: String) {
        var needIdsLocal = needIds
        if (!needIdsLocal.contains(",")) needIdsLocal += ","
        repository.getEpisodeList(needIdsLocal)
            .catch {
                episodes.value = ApiState.error(it.message.toString())
            }
            .collect {
                repository.saveEpisodesInDb(it.data!!)
                episodes.value = ApiState.success(EpisodeForListMapper().transform(it.data))
            }
    }

    private suspend fun getEpisodesDb(characterID: Int) {
        repository.getEpisodeListDb(characterID)
            .catch {
                episodes.value = ApiState.error(it.message.toString())
            }
            .collect {
                episodes.value = ApiState.success(EpisodeForListDbMapper().transform(it.data!!))
            }
    }

    private suspend fun getLocation(
        locationId: Int,
        loc: MutableStateFlow<ApiState<LocationForListDto>>
    ) {
        repository.getLocation(locationId)
            .catch {
                loc.value = ApiState.error(it.message.toString())
            }
            .collect {
                repository.saveLocationInDb(it.data!!)
                loc.value = ApiState.success(LocationForListMapper().transform(it.data))
            }
    }

    private suspend fun getLocationDb(
        locationId: Int,
        loc: MutableStateFlow<ApiState<LocationForListDto>>
    ) {
        repository.getLocationDb(locationId)
            .catch {
                loc.value = ApiState.error(it.message.toString())
            }
            .collect {
                loc.value = if (it.data != null) {
                    ApiState.success(LocationForListDbMapper().transform(it.data))
                } else {
                    ApiState.success(LocationForListDto(0, "", "", ""))
                }
            }
    }
}