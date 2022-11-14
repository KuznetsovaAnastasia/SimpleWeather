package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextClock
import com.github.skytoph.simpleweather.R

sealed class CurrentTimeUi(private val timezone: String) {
    abstract fun show(timeView: TextClock)

    class Format12Hours(private val timezone: String) : CurrentTimeUi(timezone) {
        override fun show(timeView: TextClock) {
            timeView.format12Hour = timeView.context.getString(R.string.format_12_hours_time)
            timeView.timeZone = timezone
        }
    }

    class Format24Hours(private val timezone: String) : CurrentTimeUi(timezone) {
        override fun show(timeView: TextClock) {
            timeView.format12Hour = timeView.context.getString(R.string.format_24_hours_time)
            timeView.timeZone = timezone
        }
    }
}