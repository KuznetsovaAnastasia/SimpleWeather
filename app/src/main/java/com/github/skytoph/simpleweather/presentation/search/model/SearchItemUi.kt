package com.github.skytoph.simpleweather.presentation.search.model

import android.widget.TextView
import com.github.skytoph.simpleweather.core.Matcher

sealed interface SearchItemUi : Matcher<SearchItemUi> {

    fun show(firstTextView: TextView, secondTextView: TextView)

    data class Location(
        private val id: String,
        private val title: String,
        private val subtitle: String,
    ) : SearchItemUi {

        override fun show(firstTextView: TextView, secondTextView: TextView) {
            firstTextView.text = title
            secondTextView.text = subtitle
        }

        override fun matches(item: SearchItemUi): Boolean = item is Location && id == item.id

        override fun contentMatches(item: SearchItemUi): Boolean =
            item is Location && title == item.title && subtitle == item.subtitle
    }

    data class Fail(private val message: String) : SearchItemUi {

        override fun show(firstTextView: TextView, secondTextView: TextView) {
            firstTextView.text = message
        }

        override fun matches(item: SearchItemUi): Boolean = item is Fail && message == item.message

        override fun contentMatches(item: SearchItemUi): Boolean = matches(item)
    }
}