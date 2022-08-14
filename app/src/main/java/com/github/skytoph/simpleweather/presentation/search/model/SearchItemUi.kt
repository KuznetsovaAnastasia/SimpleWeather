package com.github.skytoph.simpleweather.presentation.search.model

import android.widget.TextView
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.presentation.search.adapter.SearchLocationAdapter

sealed interface SearchItemUi : Matcher<SearchItemUi> {

    fun show(titleTextView: TextView, subtitleTextView: TextView)
    fun select(listener: SearchLocationAdapter.LocationClickListener) = Unit

    data class Location(
        private val id: String,
        private val title: String,
        private val subtitle: String,
    ) : SearchItemUi {

        override fun show(titleTextView: TextView, subtitleTextView: TextView) {
            titleTextView.text = title
            subtitleTextView.text = subtitle
        }

        // TODO: get favorite
        override fun select(listener: SearchLocationAdapter.LocationClickListener) =
            listener.open(id, false)

        override fun matches(item: SearchItemUi): Boolean = item is Location && id == item.id

        override fun contentMatches(item: SearchItemUi): Boolean =
            item is Location && title == item.title && subtitle == item.subtitle
    }

    data class Fail(private val message: String) : SearchItemUi {

        override fun show(titleTextView: TextView, subtitleTextView: TextView) {
            titleTextView.text = message
        }

        override fun matches(item: SearchItemUi): Boolean = item is Fail && message == item.message

        override fun contentMatches(item: SearchItemUi): Boolean = matches(item)
    }
}