package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import javax.inject.Inject

interface WarningsDataFilter {
    fun filter(warnings: List<WarningData>): List<WarningData>

    class Base @Inject constructor(private val timeProvider: TimeProvider) : WarningsDataFilter {

        override fun filter(warnings: List<WarningData>): List<WarningData> =
            warnings.filter { it.isNotOutdated(timeProvider.currentHoursInSeconds()) }
    }
}