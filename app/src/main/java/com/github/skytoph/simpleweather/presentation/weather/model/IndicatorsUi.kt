package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextView

data class IndicatorsUi(
    private val time: String,
    private val uv: String,
    private val pop: String,
    private val aq: String,
)  {

    fun show(
        timeTextView: TextView,
        uvTextView: TextView,
        popTextView: TextView,
        aqTextView: TextView,
    ) {
        timeTextView.text = time
        uvTextView.text = uv
        popTextView.text = pop
        aqTextView.text = aq
    }
}