package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface WarningDataToDomainMapper : Mapper<WeatherDomain.Warning> {
    fun map(
        name: String,
        startTime: Long,
        precipitationProb: Double,
        description: String,
    ): WeatherDomain.Warning

    class Base @Inject constructor() : WarningDataToDomainMapper {

        override fun map(
            name: String,
            startTime: Long,
            precipitationProb: Double,
            description: String,
        ) = WeatherDomain.Warning(name, startTime, precipitationProb, description)
    }
}
