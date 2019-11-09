package com.example.syftchallenge.di.app

import com.example.syftchallenge.MyApp
import com.example.syftchallenge.repository.Repository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(myApp: MyApp)

    fun repository():Repository
}