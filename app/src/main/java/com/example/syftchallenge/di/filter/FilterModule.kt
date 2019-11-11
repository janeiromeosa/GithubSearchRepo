package com.example.syftchallenge.di.filter

import androidx.lifecycle.ViewModelProviders
import com.example.syftchallenge.repository.Repository
import com.example.syftchallenge.view.FilterActivity
import com.example.syftchallenge.view.SearchActivity
import com.example.syftchallenge.viewmodel.SearchViewModel
import com.example.syftchallenge.viewmodel.factory.SearchViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FilterModule(private val filterActivity: FilterActivity) {

    @Provides
    @FilterScope
    fun provideSearchViewModelFactory(repository: Repository): SearchViewModelFactory {
        return SearchViewModelFactory(repository)
    }

    @Provides
    @FilterScope
    fun provideSearchViewModel(searchViewModelFactory: SearchViewModelFactory): SearchViewModel {
       return ViewModelProviders.of(filterActivity,searchViewModelFactory).get(SearchViewModel::class.java)
    }

}