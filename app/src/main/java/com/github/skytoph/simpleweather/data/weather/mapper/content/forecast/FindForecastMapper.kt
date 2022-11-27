package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.data.weather.cache.model.content.MappableToHorizon
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.MappableToForecast
import javax.inject.Inject

interface FindForecastMapper {

    fun map(hourly: List<HourlyForecastDB>, daily: List<DailyForecastDB>): MappableToForecast
    fun map(daily: List<DailyForecastDB>): MappableToHorizon

    class Base @Inject constructor(private val currentTime: CurrentTime) : FindForecastMapper {

        override fun map(
            hourly: List<HourlyForecastDB>,
            daily: List<DailyForecastDB>,
        ): MappableToForecast =
            hourly.find { it.isCurrent(currentTime.hoursInSeconds()) }
                ?: map(daily) as MappableToForecast

        override fun map(daily: List<DailyForecastDB>): MappableToHorizon =
            daily.find { it.isCurrent(currentTime.dayInSeconds()) }
                ?: daily.last()
    }
}