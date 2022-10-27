package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastFilter {
    fun filter(
        hourlyForecast: List<HourlyForecastData>,
        timezoneOffset: Int,
    ): List<HourlyForecastData>

    class Base @Inject constructor(private val timeProvider: TimeProvider) : HourlyForecastFilter {

        override fun filter(
            hourlyForecast: List<HourlyForecastData>,
            timezoneOffset: Int,
        ): List<HourlyForecastData> {
            val timeSeconds = timeProvider.currentTimeSeconds() + timezoneOffset
            val list = hourlyForecast.filter { !it.isOutdated(timeSeconds) }
            return if (list.size > FORECAST_NUMBER) list.subList(0, FORECAST_NUMBER) else list
        }

        private companion object {
            const val FORECAST_NUMBER = 24
        }
    }
}