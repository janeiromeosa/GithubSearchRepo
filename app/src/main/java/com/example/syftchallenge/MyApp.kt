package com.example.syftchallenge

import android.app.Application
import com.example.syftchallenge.di.app.AppComponent
import com.example.syftchallenge.di.app.DaggerAppComponent
import com.example.syftchallenge.di.app.NetworkModule
import com.example.syftchallenge.di.app.RepositoryModule

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        component()
    }

    fun component(): AppComponent = DaggerAppComponent.builder().networkModule(NetworkModule()).repositoryModule(
        RepositoryModule()
    ).build()
}