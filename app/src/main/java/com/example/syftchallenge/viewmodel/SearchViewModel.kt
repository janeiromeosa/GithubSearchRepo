package com.example.syftchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.syftchallenge.model.SearchResponse
import com.example.syftchallenge.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val searchResultObservable: MutableLiveData<SearchResponse> = MutableLiveData()
    private val errorObservable: MutableLiveData<String> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    fun getSearchResults(searchKeyWord: String = "org:github", language: String = "") {
        compositeDisposable.add(
            repository.getSearchResults(
                searchKeyWord + if (language != "") {
                    " language:$language"
                } else {
                    ""
                }, SORT_BY
            )
                .doOnSubscribe { }
                .doOnError { }
                .subscribe(
                    { results -> searchResultObservable.value = results },
                    { error -> errorObservable.value = error.message })
        )
    }

    fun getSearchResultsObservable() = searchResultObservable
    fun getErrorObservable() = errorObservable

    companion object {
        const val SORT_BY = "stars"
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}