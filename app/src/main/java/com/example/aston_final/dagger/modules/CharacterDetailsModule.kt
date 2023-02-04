package com.example.aston_final.dagger.modules

import com.example.aston_final.model.repository.CharacterDetailsRepository
import com.example.aston_final.utils.InternetConnectionChecker
import com.example.aston_final.viewmodel.factory.CharacterDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CharacterDetailsModule {
    @Provides
    fun provideCharacterDetailsViewModelFactory(
        characterId: Int,
        repository: CharacterDetailsRepository,
        internetChecker: InternetConnectionChecker
    ): CharacterDetailsViewModelFactory {
        return CharacterDetailsViewModelFactory(characterId, repository, internetChecker)
    }
}