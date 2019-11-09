package com.example.syftchallenge.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.syftchallenge.MyApp
import com.example.syftchallenge.R
import com.example.syftchallenge.di.search.DaggerFilterComponent
import com.example.syftchallenge.di.search.FilterModule
import com.example.syftchallenge.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.item_filter.*
import javax.inject.Inject

class FilterActivity : AppCompatActivity(){

    @Inject
    lateinit var searchViewModel: SearchViewModel
    val selectedLanguages = SparseBooleanArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_filter)

        getDependencies()

        btn_apply.setOnClickListener {
            val intent = Intent()
            intent.putStringArrayListExtra("selected languages", getSelectedLanguages())
            setResult(Activity.RESULT_OK)
        }

//        searchViewModel.getSearchResults()
    }

    private fun getSelectedLanguages(): ArrayList<String>? {
        return
    }


    private fun getDependencies() {
        DaggerFilterComponent.builder().appComponent((application as MyApp).component())
            .filterModule(
                FilterModule(this)
            ).build().inject(this)
    }

}

