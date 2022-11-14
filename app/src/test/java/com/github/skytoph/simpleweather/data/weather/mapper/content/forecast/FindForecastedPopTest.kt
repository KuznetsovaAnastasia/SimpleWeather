package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import org.junit.Assert.assertEquals
import org.junit.Test

class FindForecastedPopTest {

    private var time = 0L
    private val timeProvider = object : TimeProvider {
        override fun currentTimeInSeconds(): Long = time
        override fun currentHoursInSeconds(): Long = time
        override fun currentDayInSeconds(): Long = time
    }
    private val mapper = FindForecastedPop.Base(timeProvider)

    private val forecast = listOf(
        HourlyForecastData(10L, 10.0, 1, 0.5),
        HourlyForecastData(20L, 11.0, 2, 0.7),
        HourlyForecastData(30L, 12.0, 5, 0.8),
        HourlyForecastData(40L, 10.0, 1, 1.0),
        HourlyForecastData(50L, 11.0, 1, 0.9),
    )

    @Test
    fun find_pop_success() {
        assertEquals(0.5, mapper.map(forecast, 10L, 0.1), 0.0001)
        assertEquals(0.8, mapper.map(forecast, 30L, 0.1), 0.0001)
        assertEquals(1.0, mapper.map(forecast, 40L, 0.1), 0.0001)
        assertEquals(0.9, mapper.map(forecast, 50L, 0.1), 0.0001)
    }

    @Test
    fun find_pop_fail() {
        val currentPop = 0.1
        assertEquals(currentPop, mapper.map(forecast, 100L, currentPop), 0.0001)
    }
}