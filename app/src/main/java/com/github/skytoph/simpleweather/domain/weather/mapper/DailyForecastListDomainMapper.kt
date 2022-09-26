package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.DailyForecastData
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface DailyForecastListDomainMapper : Mapper<List<WeatherDomain.DailyForecast>> {

    fun map(forecasts: List<DailyForecastData>): List<WeatherDomain.DailyForecast>

    class Base @Inject constructor(private val mapper: DailyForecastDomainMapper) :
        DailyForecastListDomainMapper {

        override fun map(forecasts: List<DailyForecastData>): List<WeatherDomain.DailyForecast> =
            forecasts.map { it.map(mapper) }
    }
}