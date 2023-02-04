package com.example.aston_final

import android.app.Application
import com.example.aston_final.dagger.AppComponent
import com.example.aston_final.dagger.AppModule
import com.example.aston_final.dagger.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
            appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}