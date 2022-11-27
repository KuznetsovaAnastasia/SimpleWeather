package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextClock
import android.widget.TextView

data class IndicatorsUi(
    private val timeFormat: CurrentTimeUi,
    private val pop: String,
    private val uv: UltravioletIndexUi,
    private val aq: AirQualityUi,
) {

    fun show(
        timeView: TextClock,
        uvTextView: TextView,
        popTextView: TextView,
        aqTextView: TextView,
    ) {
        timeFormat.show(timeView)
        popTextView.text = pop
        uv.show(uvTextView)
        aq.show(aqTextView)
    }
}