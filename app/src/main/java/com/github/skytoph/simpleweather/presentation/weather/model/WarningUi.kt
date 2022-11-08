package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility

sealed class WarningUi(
    private val title: String,
    private val description: String,
    private val timeValue: String,
    @StringRes private val timeTitle: Int,
) : Matcher<WarningUi> {

    fun show(
        titleTextView: TextView,
        descriptionTextView: TextView,
        timeTextView: TextView,
        timeTitleTextView: TextView,
    ) {
        titleTextView.text = title
        descriptionTextView.text = description
        timeTextView.text = timeValue
        timeTitleTextView.text = timeTitleTextView.resources.getString(timeTitle)
        Visibility.Visible().apply(descriptionTextView)
    }

    override fun matches(item: WarningUi): Boolean = title == item.title
    override fun contentMatches(item: WarningUi): Boolean = item.equals(this)

    fun isDescribed(): Boolean = description.isNotBlank()

    data class Basic(
        private val event: String,
        private val description: String,
        private val timeValue: String,
        @StringRes private val timeTitle: Int,
    ) : WarningUi(event, description, timeValue, timeTitle)

    data class Rain(
        private val event: String,
        private val description: String,
        private val timeValue: String,
        @StringRes private val timeTitle: Int,
        @DrawableRes private val rainImage: Int,
        private val pop: String,
    ) : WarningUi(event, pop, timeValue, timeTitle)
}