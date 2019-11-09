package com.example.syftchallenge.di.search

import com.example.syftchallenge.di.app.AppComponent
import com.example.syftchallenge.view.SearchActivity
import dagger.Component

@SearchScope
@Component(modules = [SearchModule::class],dependencies = [AppComponent::class])
interface SearchComponent {
    fun inject(searchActivity: SearchActivity)
}