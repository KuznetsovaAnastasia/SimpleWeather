package com.github.skytoph.simpleweather.data.weather.mapper.content.indicators

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import javax.inject.Inject

interface IndicatorsDataMapper : Mapper<IndicatorsData> {

    fun map(uvi: Double, precipitationProb: Double, airQuality: Int): IndicatorsData

    class Base @Inject constructor() : IndicatorsDataMapper {

        override fun map(uvi: Double, precipitationProb: Double, airQuality: Int) =
            IndicatorsData(uvi, precipitationProb, airQuality)
    }
}
