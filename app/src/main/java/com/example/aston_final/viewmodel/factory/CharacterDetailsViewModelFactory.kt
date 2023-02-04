package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final.model.repository.CharacterDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.CharacterDetailsViewModel

class CharacterDetailsViewModelFactory(
    private val characterID: Int,
    val repository: CharacterDetailsRepository,
    private val internetChecker: InternetConnectionChecker
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(characterID, repository, internetChecker) as T
    }
}