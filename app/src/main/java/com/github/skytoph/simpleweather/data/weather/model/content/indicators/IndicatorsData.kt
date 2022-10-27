package com.github.skytoph.simpleweather.data.weather.model.content.indicators

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators.IndicatorsDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.IndicatorsDB
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.IndicatorsDomain

data class IndicatorsData(
    private val uvi: Double,
    private val precipitationProb: Double,
    private val airQuality: Int,
) : Mappable<IndicatorsDomain, IndicatorsDataToDomainMapper>,
    MappableToDB.Embedded<IndicatorsDB, IndicatorsDBMapper>,
    MappableTo<Double> {

    override fun map(mapper: IndicatorsDataToDomainMapper): IndicatorsDomain =
        mapper.map(uvi, precipitationProb, airQuality)

    override fun map(mapper: IndicatorsDBMapper): IndicatorsDB =
        mapper.map(uvi, precipitationProb, airQuality)

    override fun map(): Double = precipitationProb
}