package com.example.aston_final.dagger

import android.app.Application
import com.example.aston_final.MainViewModelFactory
import com.example.aston_final.utils.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Application) {
    @Provides
    fun provideContext(): Application {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(resourceProvider: ResourceProvider): MainViewModelFactory {
        return MainViewModelFactory(resourceProvider)
    }
}