package com.example.syftchallenge.net

import com.example.syftchallenge.common.Constants.Companion.SEARCH_ENDPOINT
import com.example.syftchallenge.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(SEARCH_ENDPOINT)
    fun getSearchResults(
        @Query("q") searchKeyWord: String,
        @Query("sort") sortBy: String?
    ): Single<SearchResponse>
}