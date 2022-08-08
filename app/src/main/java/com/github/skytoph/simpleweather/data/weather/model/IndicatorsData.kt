package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.IndicatorsDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.IndicatorsDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class IndicatorsData(
    private val time: Long,
    private val uvi: Double,
    private val precipitationProb: Double,
    private val airQuality: Int,
) : Mappable<WeatherDomain.Indicators, IndicatorsDataToDomainMapper>,
    MappableToDB.Embedded<IndicatorsDB, IndicatorsDBMapper> {

    override fun map(mapper: IndicatorsDataToDomainMapper): WeatherDomain.Indicators =
        mapper.map(time, uvi, precipitationProb, airQuality)

    override fun map(mapper: IndicatorsDBMapper): IndicatorsDB =
        mapper.map(time, uvi, precipitationProb, airQuality)
}
