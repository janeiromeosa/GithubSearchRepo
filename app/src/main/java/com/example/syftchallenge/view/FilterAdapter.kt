package com.example.syftchallenge.view

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.syftchallenge.R
import com.example.syftchallenge.model.Language
import kotlinx.android.synthetic.main.item_filter.view.*
import kotlinx.android.synthetic.main.list_filter.view.*

class FilterAdapter(val languageList: List<Language>) :RecyclerView.Adapter<FilterAdapter.FilterViewHoldler>(){

    val selectedLanguageList = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHoldler {
        return FilterViewHoldler(LayoutInflater.from(parent.context).inflate(R.layout.list_filter, parent, false))
    }

    override fun getItemCount() = languageList.size

    override fun onBindViewHolder(holder: FilterViewHoldler, position: Int) {
        holder.bind(languageList[holder.adapterPosition], {isSelected ->
            selectedLanguageList.put(holder.adapterPosition, isSelected)
        })

    }

    fun getSelectedCodingLanguages(): ArrayList<String>? {
        return ArrayList(
            languageList.filterIndexed { index, _ ->
                selectedLanguageList[index]
            }.map{it.codingLanguage}
        )
    }


    class FilterViewHoldler(view: View): RecyclerView.ViewHolder(view) {

        fun bind(language: Language, isCheckBoxSelected: (language: Boolean) -> Unit) {
            itemView.tv_languages.text = language.codingLanguage
            itemView.cb_filter_selected.isChecked = language.isSelected
            itemView.cb_filter_selected.setOnCheckedChangeListener{ _, isChecked ->
                isCheckBoxSelected
            }
        }
    }
}