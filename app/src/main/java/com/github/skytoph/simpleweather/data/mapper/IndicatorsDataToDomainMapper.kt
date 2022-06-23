package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.AirQualityData
import com.github.skytoph.simpleweather.data.model.IndicatorsData
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

interface IndicatorsDataToDomainMapper : Mapper<WeatherDomain.Indicators> {
    fun map(time: Long, uvi: Double, precipitationProb: Double): WeatherDomain.Indicators

//    fun map(indicators: IndicatorsData): WeatherDomain.Indicators

    class Base(private val airQuality: AirQualityData) : IndicatorsDataToDomainMapper {

//        override fun map(
//            indicators: IndicatorsData
//        ): WeatherDomain.Indicators {
//
//            val indicator = indicators.map()
//            val airQualityIndex = airQuality.map()
//
//            return WeatherDomain.Indicators(
//                indicator.first,
//                indicator.second,
//                indicator.third,
//                airQualityIndex
//            )
//        }
        override fun map(
            time: Long,
            uvi: Double,
            precipitationProb: Double
        ) = WeatherDomain.Indicators(time, uvi, precipitationProb, airQuality.map())
    }
}
