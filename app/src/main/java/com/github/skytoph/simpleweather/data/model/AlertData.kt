package com.github.skytoph.simpleweather.data.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.mapper.WarningDataToDomainMapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

data class AlertData(
    private val name: String,
    private val startTime: Long,
    private val precipitationProb: Double,
) :
    Mappable<WeatherDomain.Warning, WarningDataToDomainMapper> {

    override fun map(mapper: WarningDataToDomainMapper): WeatherDomain.Warning =
        mapper.map(name, startTime, precipitationProb)
}
