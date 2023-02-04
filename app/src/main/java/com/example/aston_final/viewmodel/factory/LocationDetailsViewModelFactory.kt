package com.example.aston_final.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aston_final.model.repository.LocationDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.LocationDetailsViewModel
import kotlinx.coroutines.CoroutineDispatcher

class LocationDetailsViewModelFactory(
    private val locationID: Int,
    val repository: LocationDetailsRepository,
    private val internetChecker: InternetConnectionChecker,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationDetailsViewModel(locationID, repository, internetChecker, dispatcher) as T
    }
}