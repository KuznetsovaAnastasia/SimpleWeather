package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface FindForecastedPop {
    fun map(
        hourlyForecast: List<HourlyForecastData>,
        startTime: Long,
        currentPop: Double,
    ): Double

    class Base @Inject constructor(private val timeProvider: TimeProvider) : FindForecastedPop {

        override fun map(
            hourlyForecast: List<HourlyForecastData>,
            startTime: Long,
            currentPop: Double,
        ): Double {
            if (startTime < timeProvider.currentHoursInSeconds()) return currentPop

            val mapper = object : WarningPopMapper {
                override fun map(pop: Double): Double = pop
            }
            return hourlyForecast.find { it.isTimeForecasted(startTime) }?.map(mapper) ?: currentPop
        }
    }
}

interface WarningPopMapper {
    fun map(pop: Double): Double
}