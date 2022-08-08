package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.IndicatorsData

interface IndicatorsDataMapper : Mapper<IndicatorsData> {

    fun map(time: Long, uvi: Double, precipitationProb: Double, airQuality: Int): IndicatorsData

    class Base : IndicatorsDataMapper {

        override fun map(time: Long, uvi: Double, precipitationProb: Double, airQuality: Int) =
            IndicatorsData(time, uvi, precipitationProb, airQuality)
    }
}
