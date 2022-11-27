package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.HourlyForecastDB
import org.junit.Assert.assertTrue
import org.junit.Test

class FindForecastMapperTest {

    private var time = 0L
    private val currentTime = object : CurrentTime {
        override fun hoursInSeconds(): Long = time
        override fun dayInSeconds(): Long = time
        override fun inSeconds(): Long = time
        override fun inMillis(): Long = time
    }
    private val mapper = FindForecastMapper.Base(currentTime)

    private val hourly = listOf(hourly(100L, 10.0), hourly(200L, 20.0), hourly(300L, 30.0))
    private val daily = listOf(daily(1000L, 9.0), daily(2000L, 12.0), daily(3000L, 13.0))

    @Test
    fun find_hourly() {
        time = 200L
        val actual = mapper.map(hourly, daily)
        assertTrue(actual is HourlyForecastDB)
        assertTrue(actual.isCurrent(time))
    }

    @Test
    fun find_daily() {
        time = 3_000L
        val actual = mapper.map(hourly, daily)
        assertTrue(actual is DailyForecastDB)
        assertTrue(actual.isCurrent(time))
    }

    @Test
    fun find_last() {
        time = 10_000L
        val actual = mapper.map(hourly, daily)
        assertTrue(actual is DailyForecastDB)
        assertTrue(!actual.isCurrent(time))
        assertTrue(actual.isCurrent(3_000L))
    }

    private fun hourly(time: Long, temp: Double) = HourlyForecastDB().apply {
        this.time = time
        this.temp = temp
    }

    private fun daily(time: Long, temp: Double) = DailyForecastDB().apply {
        this.time = time
        this.tempMax = temp
        this.tempMin = temp
    }
}