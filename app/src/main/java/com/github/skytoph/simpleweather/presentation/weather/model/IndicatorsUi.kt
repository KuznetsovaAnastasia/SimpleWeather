package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextClock
import android.widget.TextView

data class IndicatorsUi(
    private val timeFormat: CurrentTimeUi,
    private val uv: String,
    private val pop: String,
    private val aq: String,
) {

    fun show(
        timeView: TextClock,
        uvTextView: TextView,
        popTextView: TextView,
        aqTextView: TextView,
    ) {
        timeFormat.show(timeView)
        uvTextView.text = uv
        popTextView.text = pop
        aqTextView.text = aq
    }
}