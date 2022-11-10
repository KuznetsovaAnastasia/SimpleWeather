package com.github.skytoph.simpleweather.presentation.search.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseAdapter
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseDiffUtil
import com.github.skytoph.simpleweather.core.presentation.adapter.BaseViewHolder
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi

class SearchHistoryAdapter(private val listener: ClickListener) :
    BaseAdapter<SearchHistoryUi, BaseViewHolder<SearchHistoryUi>>(SearchItemDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<SearchHistoryUi> =
        SearchItemViewHolder(R.layout.search_history_item.inflateView(parent), listener)

    class SearchItemViewHolder(itemView: View, private val listener: ClickListener) :
        BaseViewHolder<SearchHistoryUi>(itemView) {

        override fun bind(item: SearchHistoryUi) {
            val titleTextView = itemView.findViewById<TextView>(R.id.search_history_location)
            item.show(titleTextView)
            itemView.setOnClickListener { item.select(listener) }
        }
    }

    fun interface ClickListener {
        fun select(id: String, title: String)
    }

    class SearchItemDiffCallback : BaseDiffUtil<SearchHistoryUi>()
}
