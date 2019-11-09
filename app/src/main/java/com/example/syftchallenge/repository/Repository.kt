package com.example.syftchallenge.repository

import com.example.syftchallenge.model.SearchResponse
import io.reactivex.Single

interface Repository {

    fun getSearchResults(
        searchKeyWord: String,
        sortBy: String?
    ):Single<SearchResponse>
}