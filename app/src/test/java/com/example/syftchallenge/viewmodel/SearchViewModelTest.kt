package com.example.syftchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.syftchallenge.model.Item
import com.example.syftchallenge.model.SearchResponse
import com.example.syftchallenge.repository.Repository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {
    @Rule
    @JvmField
    var testRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

   private lateinit var viewModel: SearchViewModel

    private val searchResultObserver:Observer<SearchResponse> = mock()
    private val errorObserver:Observer<String> = mock()
    private val item = Item(1,"repo","repo","kotlin",true,"desc")

    @Before
    fun setUp(){
        viewModel = SearchViewModel(repository)

        viewModel.getSearchResultsObservable().observeForever(searchResultObserver)
        viewModel.getErrorObservable().observeForever(errorObserver)

    }

    @Test
    fun searchReturnsResult(){

        val searchResponse = SearchResponse(1,false, mutableListOf(item))
        val searchKeyWord = "Android"
        val sortBy = "stars"

        `when`(repository.getSearchResults(searchKeyWord, sortBy)).thenReturn(Single.just(searchResponse))

        viewModel.getSearchResults(searchKeyWord)

        verify(searchResultObserver, times(1)).onChanged(searchResponse)
        verify(errorObserver, times(0)).onChanged(ArgumentMatchers.anyString())
    }

}