package com.example.aston_final.dagger.modules

import com.example.aston_final.model.repository.EpisodeDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.factory.EpisodeDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class EpisodeDetailsModule {

    @Provides
    fun provideEpisodeDetailsViewModelFactory(
        episodeId: Int,
        repository: EpisodeDetailsRepository,
        internetChecker: InternetConnectionChecker
    ): EpisodeDetailsViewModelFactory {
        return EpisodeDetailsViewModelFactory(episodeId, repository, internetChecker)
    }
}