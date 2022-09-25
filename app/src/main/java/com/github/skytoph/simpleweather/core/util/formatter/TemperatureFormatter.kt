package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import javax.inject.Inject
import kotlin.math.roundToInt

interface TemperatureFormatter {
    fun format(temp: Double): String

    class Base @Inject constructor(private val resourceProvider: ResourceProvider) :
        TemperatureFormatter {

        override fun format(temp: Double): String {
            val roundedTemp = temp.roundToInt().toString()
            return resourceProvider.string(R.string.degrees_celsius, roundedTemp)
        }
    }
}