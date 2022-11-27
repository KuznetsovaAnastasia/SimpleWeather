package com.github.skytoph.simpleweather.presentation.weather.model

import androidx.annotation.StringRes
import com.github.skytoph.simpleweather.R

data class UltravioletIndexUi(private val value: String, @StringRes private val tooltipId: Int) :
    TextWithTooltipUi(value, R.string.tooltip_ultraviolet_index_text, tooltipId)