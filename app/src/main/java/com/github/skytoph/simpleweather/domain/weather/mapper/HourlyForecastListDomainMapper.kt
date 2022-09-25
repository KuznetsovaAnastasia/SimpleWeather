package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface HourlyForecastListDomainMapper : Mapper<List<WeatherDomain.HourlyForecast>> {

    fun map(forecasts: List<HourlyForecastData>): List<WeatherDomain.HourlyForecast>

    class Base @Inject constructor(private val mapper: HourlyForecastDomainMapper) :
        HourlyForecastListDomainMapper {

        override fun map(forecasts: List<HourlyForecastData>): List<WeatherDomain.HourlyForecast> =
            forecasts.map { it.map(mapper) }
    }
}