package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import javax.inject.Inject
import kotlin.math.roundToInt

interface TemperatureFormatter {
    fun format(temp: Double): String

    class Base @Inject constructor(
        private val resources: ResourceProvider,
        private val format: TemperatureFormat,
    ) : TemperatureFormatter, TemperatureConverter() {

        override fun format(temp: Double): String {
            val roundedTemp = (if (format.isCelsiusChosen()) temp else fahrenheit(temp))
                .roundToInt().toString()
            return resources.string(R.string.temperature_degrees, roundedTemp)
        }
    }
}

abstract class TemperatureConverter {
    fun fahrenheit(celsius: Double): Double = celsius * 5 / 9 + 32
}