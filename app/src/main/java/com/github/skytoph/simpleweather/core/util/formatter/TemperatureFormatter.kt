package com.github.skytoph.simpleweather.core.util.formatter

import javax.inject.Inject
import kotlin.math.roundToInt

interface TemperatureFormatter {
    fun format(temp: Double): String

    class Base @Inject constructor() : TemperatureFormatter {

        override fun format(temp: Double): String = temp.roundToInt().toString()
    }
}