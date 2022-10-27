package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastFilter {
    fun filter(hourlyForecast: List<HourlyForecastData>): List<HourlyForecastData>

    class Base @Inject constructor(private val timeProvider: TimeProvider) : HourlyForecastFilter {

        override fun filter(hourlyForecast: List<HourlyForecastData>): List<HourlyForecastData> {
            val timeSeconds = timeProvider.currentHoursInSeconds()
            val list = hourlyForecast.filter { it.isNotOutdated(timeSeconds) }
            return if (list.size > FORECAST_NUMBER) list.subList(0, FORECAST_NUMBER) else list
        }

        private companion object {
            const val FORECAST_NUMBER = 24
        }
    }
}