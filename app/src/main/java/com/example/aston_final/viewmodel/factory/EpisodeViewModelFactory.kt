package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.example.aston_final.model.dto.EpisodeForListDto
import com.example.aston_final.viewmodel.EpisodeViewModel
import kotlinx.coroutines.flow.Flow

class EpisodeViewModelFactory(private val dataSource: Flow<PagingData<EpisodeForListDto>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeViewModel(dataSource) as T
    }
}