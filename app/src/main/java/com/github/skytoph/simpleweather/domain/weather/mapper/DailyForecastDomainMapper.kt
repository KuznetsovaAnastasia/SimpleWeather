package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface DailyForecastDomainMapper : Mapper<WeatherDomain.DailyForecast> {

    fun map(
        time: Long,
        temp: Pair<Double, Double>,
        weatherId: Int,
        pop: Double,
    ): WeatherDomain.DailyForecast

    class Base @Inject constructor() : DailyForecastDomainMapper {

        override fun map(time: Long, temp: Pair<Double, Double>, weatherId: Int, pop: Double) =
            WeatherDomain.DailyForecast(time, temp, weatherId, pop)
    }
}