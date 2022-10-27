package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.IndicatorsDB
import javax.inject.Inject

interface IndicatorsDBMapper : Mapper<IndicatorsDB> {

    fun map(uvi: Double, precipitationProb: Double, airQuality: Int): IndicatorsDB

    class Base @Inject constructor() : IndicatorsDBMapper {
        override fun map(
            uvi: Double,
            precipitationProb: Double,
            airQuality: Int,
        ) = IndicatorsDB().apply {
            this.uvi = uvi
            this.precipitationProb = precipitationProb
            this.airQuality = airQuality
        }
    }
}
