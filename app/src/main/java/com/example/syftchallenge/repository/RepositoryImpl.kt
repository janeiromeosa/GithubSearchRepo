package com.example.syftchallenge.repository

import com.example.syftchallenge.model.SearchResponse
import com.example.syftchallenge.net.WebService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val webService: WebService) : Repository {


    override fun getSearchResults(
        searchKeyWord: String,
        sortBy: String?
    ): Single<SearchResponse> {
        return webService.getSearchResults(searchKeyWord,sortBy)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}