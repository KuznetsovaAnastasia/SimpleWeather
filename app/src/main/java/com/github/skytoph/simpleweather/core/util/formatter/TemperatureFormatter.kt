package com.github.skytoph.simpleweather.core.util.formatter

import kotlin.math.roundToInt

interface TemperatureFormatter {
    fun format(temp: Double): String

    class Base: TemperatureFormatter {

        override fun format(temp: Double): String = temp.roundToInt().toString()
    }
}