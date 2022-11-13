package com.github.skytoph.simpleweather.data.weather.mapper.content.indicators

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators.MappableToIndicator
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import javax.inject.Inject

interface ForecastToIndicatorsMapper : Mapper<IndicatorsData> {

    fun map(forecast: MappableToIndicator, airQuality: Int): IndicatorsData

    class Base @Inject constructor() : ForecastToIndicatorsMapper {

        override fun map(forecast: MappableToIndicator, airQuality: Int): IndicatorsData {
            val mapper = object : IndicatorsDataMapper {
                override fun map(uvi: Double, precipitationProb: Double) =
                    IndicatorsData(uvi, precipitationProb, airQuality)
            }
            return forecast.map(mapper)
        }
    }
}