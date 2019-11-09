package com.example.syftchallenge.di.app

import com.example.syftchallenge.net.WebService
import com.example.syftchallenge.repository.Repository
import com.example.syftchallenge.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(webService: WebService):Repository{
        return RepositoryImpl(webService)
    }
}