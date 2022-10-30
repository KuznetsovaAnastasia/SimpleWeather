package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.view.horizon.HorizonView

data class HorizonUi(
    private val sunrise: String,
    private val sunset: String,
    private val dayLength: Pair<Int, Int>,
    private val remainingDaylight: Pair<Int, Int>,
    private val sunPositionValue: Double,
)  {

    fun show(
        horizonView: HorizonView,
        dayLengthTextView: TextView,
        daylightTextView: TextView,
    ) {
        horizonView.setValues(sunrise, sunset, sunPositionValue)
        val resources = horizonView.context.resources
        dayLengthTextView.text = resources.getString(R.string.time_duration_format, dayLength.first, dayLength.second)
        daylightTextView.text = resources.getString(R.string.time_duration_format, remainingDaylight.first, remainingDaylight.second)
    }
}