package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

interface IndicatorsDataToDomainMapper : Mapper<WeatherDomain.Indicators> {
    fun map(
        time: Long,
        uvi: Double,
        precipitationProb: Double,
        airQuality: Int,
    ): WeatherDomain.Indicators

    class Base : IndicatorsDataToDomainMapper {

        override fun map(
            time: Long,
            uvi: Double,
            precipitationProb: Double,
            airQuality: Int,
        ) = WeatherDomain.Indicators(time, uvi, precipitationProb, airQuality)
    }
}
