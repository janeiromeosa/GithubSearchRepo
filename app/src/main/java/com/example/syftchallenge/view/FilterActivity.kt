package com.example.syftchallenge.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syftchallenge.R
import com.example.syftchallenge.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.list_filter.*
import javax.inject.Inject

class FilterActivity : AppCompatActivity() {

    @Inject
    lateinit var searchViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_filter)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rv_filter.layoutManager = LinearLayoutManager(this)

        val filterAdapter = FilterAdapter(
            listOf(
                "C++",
                "JavaScript",
                "Assembly",
                "Java"), intent?.getStringExtra("selected_lan")?: ""
        )
        rv_filter.adapter = filterAdapter

        btn_apply.setOnClickListener {
            val intent = Intent()
            intent.putExtra("selected_apply", filterAdapter.selectedLanguage)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

}

