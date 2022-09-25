package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDBMapper : Mapper<List<HourlyForecastDB>> {

    fun map(forecasts: List<HourlyForecastData>): List<HourlyForecastDB>

    class Base @Inject constructor(private val mapper: HourlyForecastDBMapper) :
        HourlyForecastListDBMapper {

        override fun map(forecasts: List<HourlyForecastData>): List<HourlyForecastDB> =
            forecasts.map { it.map(mapper) }
    }
}