package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.example.aston_final.model.dto.CharacterForListDto
import com.example.aston_final.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.Flow

class CharacterViewModelFactory(private val dataSource: Flow<PagingData<CharacterForListDto>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(dataSource) as T
    }
}