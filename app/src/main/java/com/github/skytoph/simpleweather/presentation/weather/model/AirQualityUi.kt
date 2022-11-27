package com.github.skytoph.simpleweather.presentation.weather.model

import androidx.annotation.StringRes
import com.github.skytoph.simpleweather.R

data class AirQualityUi(private val value: String, @StringRes private val tooltipValueId: Int) :
    TextWithTooltipUi(value, R.string.tooltip_air_quality_text, tooltipValueId)