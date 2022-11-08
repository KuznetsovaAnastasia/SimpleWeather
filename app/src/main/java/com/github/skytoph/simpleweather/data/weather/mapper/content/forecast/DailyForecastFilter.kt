package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import javax.inject.Inject

interface DailyForecastFilter {
    fun filter(forecast: List<DailyForecastData>): List<DailyForecastData>

    class Base @Inject constructor(private val timeProvider: TimeProvider) : DailyForecastFilter {

        override fun filter(forecast: List<DailyForecastData>): List<DailyForecastData> {
            val dayInSeconds = timeProvider.currentDayInSeconds()
            return forecast.filter { it.isNotOutdated(dayInSeconds) }
        }
    }
}