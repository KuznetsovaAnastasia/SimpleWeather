package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.IndicatorsDB
import javax.inject.Inject

interface IndicatorsDBMapper : Mapper<IndicatorsDB> {

    fun map(time: Long, uvi: Double, precipitationProb: Double, airQuality: Int): IndicatorsDB

    class Base @Inject constructor() : IndicatorsDBMapper {
        override fun map(
            time: Long,
            uvi: Double,
            precipitationProb: Double,
            airQuality: Int,
        ) = IndicatorsDB().apply {
            this.time = time
            this.uvi = uvi
            this.precipitationProb = precipitationProb
            this.airQuality = airQuality
        }
    }
}
