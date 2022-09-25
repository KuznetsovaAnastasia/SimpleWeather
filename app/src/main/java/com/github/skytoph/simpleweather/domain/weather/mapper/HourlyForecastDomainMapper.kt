package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface HourlyForecastDomainMapper : Mapper<WeatherDomain.HourlyForecast> {

    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): WeatherDomain.HourlyForecast

    class Base @Inject constructor() : HourlyForecastDomainMapper {

        override fun map(time: Long, temp: Double, weatherId: Int, pop: Double) =
            WeatherDomain.HourlyForecast(time, temp, weatherId, pop)
    }
}