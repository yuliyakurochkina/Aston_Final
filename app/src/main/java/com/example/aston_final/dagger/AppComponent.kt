package com.example.aston_final.dagger

import com.example.aston_final.MainActivity
import com.example.aston_final.dagger.modules.DataModule
import com.example.aston_final.dagger.modules.RetrofitModule
import com.example.aston_final.dagger.subcomponents.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DataModule::class, RetrofitModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun getEpisodeComponentBuilder(): EpisodeComponent.Builder
    fun getEpisodeDetailsComponentBuilder(): EpisodeDetailsComponent.Builder
    fun getCharacterComponentBuilder(): CharacterComponent.Builder
    fun getCharacterDetailsComponentBuilder(): CharacterDetailsComponent.Builder
    fun getLocationComponentBuilder(): LocationComponent.Builder
    fun getLocationDetailsComponentBuilder(): LocationDetailsComponent.Builder
}