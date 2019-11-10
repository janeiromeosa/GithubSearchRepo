package com.example.syftchallenge.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syftchallenge.MyApp
import com.example.syftchallenge.R
import com.example.syftchallenge.di.search.DaggerSearchComponent
import com.example.syftchallenge.di.search.SearchModule
import com.example.syftchallenge.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

private const val returnCode = 1001


class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var searchViewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    var languages: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        getDependencies()
        setupRecyclerView()
        getObservableStates()
        textChangedSearchResults()
        setFilterClickListener()
    }

    fun setFilterClickListener() {
        tv_filter.setOnClickListener {
            Toast.makeText(applicationContext, "This item was clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@SearchActivity, FilterActivity::class.java)
            this@SearchActivity.startActivityForResult(intent, returnCode)
        }
    }

    private fun resetData() {
        rv_list.visibility = View.GONE
        tv_statistics.text = ""
    }

    private fun getDependencies() {
        DaggerSearchComponent.builder().appComponent((application as MyApp).component())
            .searchModule(
                SearchModule(this)
            ).build().inject(this)
    }

    private fun setupRecyclerView() {

        searchAdapter = SearchAdapter(mutableListOf())

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = searchAdapter
    }

    private fun getObservableStates() {
        searchViewModel.getSearchResultsObservable().observe(this, Observer {
            if (it.totalCount == 0) {
                tv_statistics.text = getString(R.string.no_results)
                rv_list.visibility = View.GONE
            } else {
                tv_statistics.text = resources.getString(R.string.hits, it.totalCount.toString())
                rv_list.visibility = View.VISIBLE

            }
            searchAdapter.apply {
                itemsList.clear()
                itemsList.addAll(it.items)
            }
            searchAdapter.notifyDataSetChanged()
        })

        searchViewModel.getErrorObservable().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            tv_statistics.text = getString(R.string.something_went_wrong)
            rv_list.visibility = View.GONE
        })
    }

    private fun textChangedSearchResults() {
        eT_search.doOnTextChanged { text, _, _, _ ->
            if (text.toString().isEmpty()) {
                resetData()
                return@doOnTextChanged
            }
            searchViewModel.getSearchResults(text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            resultCode -> {
                if (resultCode == Activity.RESULT_OK) {

                    languages = data?.getStringExtra("selected")?:""
//                    searchViewModel.getSearchResults(eT_search.text)
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }


    }
}
