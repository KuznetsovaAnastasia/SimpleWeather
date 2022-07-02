package com.github.skytoph.simpleweather.data.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.mapper.IndicatorsDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class IndicatorsData(
    private val time: Long,
    private val uvi: Double,
    private val precipitationProb: Double
): Mappable<WeatherDomain.Indicators, IndicatorsDataToDomainMapper> {

    //    override fun map() = Triple(time, uvi, precipitationProb)
    override fun map(mapper: IndicatorsDataToDomainMapper): WeatherDomain.Indicators =
        mapper.map(time, uvi, precipitationProb)
}
