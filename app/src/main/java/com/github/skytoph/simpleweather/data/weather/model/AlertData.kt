package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.mapper.WarningDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class AlertData(
    private val name: String,
    private val startTime: Long,
    private val precipitationProb: Double,
    private val description: String,
) : Mappable<WeatherDomain.Warning, WarningDataToDomainMapper> {

    override fun map(mapper: WarningDataToDomainMapper): WeatherDomain.Warning =
        mapper.map(name, startTime, precipitationProb, description)
}
