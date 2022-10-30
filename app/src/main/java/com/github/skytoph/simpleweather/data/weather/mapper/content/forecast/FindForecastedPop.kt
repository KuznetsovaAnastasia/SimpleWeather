package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface FindForecastedPop {
    fun map(hourlyForecast: List<HourlyForecastData>, time: Long, currentPop: Double): Double

    class Base @Inject constructor() : FindForecastedPop {

        override fun map(
            hourlyForecast: List<HourlyForecastData>,
            time: Long,
            currentPop: Double,
        ): Double {
            val mapper = object : WarningPopMapper {
                override fun map(pop: Double): Double = pop
            }
            return hourlyForecast.find { it.isTimeForecasted(time) }?.map(mapper) ?: currentPop
        }
    }
}

interface WarningPopMapper {
    fun map(pop: Double): Double
}