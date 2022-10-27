package com.github.skytoph.simpleweather.presentation.weather

import android.widget.TextView
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility

sealed class WarningUi(
    private val title: String,
    private val description: String,
    private val startTime: String,
) : Matcher<WarningUi> {

    fun show(
        titleTextView: TextView,
        descriptionTextView: TextView,
        startTimeTextView: TextView,
    ) {
        titleTextView.text = title
        descriptionTextView.text = description
        startTimeTextView.text = startTime
        Visibility.Visible().apply(descriptionTextView)
    }

    override fun matches(item: WarningUi): Boolean = title == item.title
    override fun contentMatches(item: WarningUi): Boolean = item.equals(this)

    fun isDescribed(): Boolean = description.isNotBlank()

    data class Basic(
        private val event: String,
        private val description: String,
        private val startTime: String,
    ) : WarningUi(event, description, startTime)

    data class Rain(
        private val event: String,
        private val description: String,
        private val startTime: String,
        private val rainImage: Int,
        private val pop: String,
    ) : WarningUi(event, pop, startTime)
}