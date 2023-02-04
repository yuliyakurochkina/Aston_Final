package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.example.aston_final.model.dto.LocationForListDto
import com.example.aston_final.viewmodel.LocationViewModel
import kotlinx.coroutines.flow.Flow

class LocationViewModelFactory(private val dataSource: Flow<PagingData<LocationForListDto>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(dataSource) as T
    }
}