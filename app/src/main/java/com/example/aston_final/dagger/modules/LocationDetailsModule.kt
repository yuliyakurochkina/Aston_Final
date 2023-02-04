package com.example.aston_final.dagger.modules

import com.example.aston_final.model.repository.LocationDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.factory.LocationDetailsViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class LocationDetailsModule {

    @Provides
    fun provideLocationDetailsViewModelFactory(
        locationId: Int,
        repository: LocationDetailsRepository,
        internetChecker: InternetConnectionChecker,
        dispatcher: CoroutineDispatcher
    ): LocationDetailsViewModelFactory {
        return LocationDetailsViewModelFactory(locationId, repository, internetChecker, dispatcher)
    }
}