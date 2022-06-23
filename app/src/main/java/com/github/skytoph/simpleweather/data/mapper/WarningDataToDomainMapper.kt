package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

interface WarningDataToDomainMapper : Mapper<WeatherDomain.Warning> {
    fun map(name: String, startTime: Long, precipitationProb: Double): WeatherDomain.Warning

    class Base : WarningDataToDomainMapper {

        override fun map(name: String, startTime: Long, precipitationProb: Double) =
            WeatherDomain.Warning(name, startTime, precipitationProb)
    }
}
