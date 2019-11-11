package com.example.syftchallenge.di.filter

import com.example.syftchallenge.di.app.AppComponent
import com.example.syftchallenge.view.FilterActivity
import com.example.syftchallenge.view.SearchActivity
import dagger.Component

@FilterScope
@Component(modules = [FilterModule::class],dependencies = [AppComponent::class])
interface FilterComponent {
    fun inject(filterActivity: FilterActivity)
}