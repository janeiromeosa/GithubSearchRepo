package com.example.syftchallenge.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syftchallenge.MyApp
import com.example.syftchallenge.R
import com.example.syftchallenge.di.search.DaggerFilterComponent
import com.example.syftchallenge.di.search.FilterModule
import com.example.syftchallenge.model.Language
import com.example.syftchallenge.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.list_filter.*
import javax.inject.Inject

class FilterActivity : AppCompatActivity(){

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_filter)

        getDependencies()
        setupRecyclerView()
    }


    private fun getDependencies() {
        DaggerFilterComponent.builder().appComponent((application as MyApp).component())
            .filterModule(
                FilterModule(this)
            ).build().inject(this)
    }

    private fun setupRecyclerView() {
        rv_filter.layoutManager = LinearLayoutManager(this)

        val filterAdapter = FilterAdapter(listOf(
            Language("C++", false),
            Language("JavaScript", true),
            Language("Assembly", false),
            Language("Java", false)
        ))

        rv_filter.adapter = filterAdapter

        btn_apply.setOnClickListener {
            val intent = Intent()
            intent.putStringArrayListExtra("selected", filterAdapter.getSelectedCodingLanguages())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}

