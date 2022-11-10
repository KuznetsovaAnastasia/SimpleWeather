package com.github.skytoph.simpleweather.presentation.search.model

import android.widget.TextView
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.presentation.search.adapter.SearchHistoryAdapter

data class SearchHistoryUi(
    private val id: String,
    private val location: String,
) : Matcher<SearchHistoryUi> {

    fun show(title: TextView) {
        title.text = location
    }

    fun select(listener: SearchHistoryAdapter.ClickListener) = listener.select(id, location)

    override fun matches(item: SearchHistoryUi): Boolean = id == item.id

    override fun contentMatches(item: SearchHistoryUi): Boolean = location == item.location
}