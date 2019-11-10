package com.example.syftchallenge.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.syftchallenge.R
import kotlinx.android.synthetic.main.item_filter.view.*
import kotlinx.android.synthetic.main.list_filter.view.*

class FilterAdapter(val languageList: List<String>, var selectedLanguage: String) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHoldler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHoldler {
        return FilterViewHoldler(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_filter,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = languageList.size

    override fun onBindViewHolder(holder: FilterViewHoldler, position: Int) {
        holder.bind(languageList[holder.adapterPosition], selectedLanguage) { isSelected ->
            selectedLanguage = isSelected
            notifyDataSetChanged()
        }

    }

    class FilterViewHoldler(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            language: String,
            selectedLanguage: String,
            isRadioButtonSelected: (language: String) -> Unit
        ) {
            itemView.tv_languages.text = language
            itemView.rb_filter_selected.isChecked = language == selectedLanguage
            itemView.setOnClickListener {
                isRadioButtonSelected(language)
            }
            itemView.rb_filter_selected.setOnClickListener {
                isRadioButtonSelected(language)
            }

        }
    }
}