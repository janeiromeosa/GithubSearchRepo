package com.example.syftchallenge.di.search

import androidx.lifecycle.ViewModelProviders
import com.example.syftchallenge.repository.Repository
import com.example.syftchallenge.view.SearchActivity
import com.example.syftchallenge.viewmodel.SearchViewModel
import com.example.syftchallenge.viewmodel.factory.SearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val searchActivity: SearchActivity) {

    @Provides
    @SearchScope
    fun provideSearchViewModelFactory(repository: Repository): SearchViewModelFactory {
        return SearchViewModelFactory(repository)
    }

    @Provides
    @SearchScope
    fun provideSearchViewModel(searchViewModelFactory: SearchViewModelFactory): SearchViewModel {
       return ViewModelProviders.of(searchActivity,searchViewModelFactory).get(SearchViewModel::class.java)
    }

}