package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import javax.inject.Inject

interface IndicatorsCloudToDataMapper : Mapper<IndicatorsData> {

    fun map(uvi: Double, precipitationProb: Double, airQuality: Int): IndicatorsData

    class Base @Inject constructor() : IndicatorsCloudToDataMapper {

        override fun map(uvi: Double, precipitationProb: Double, airQuality: Int) =
            IndicatorsData(uvi, precipitationProb, airQuality)
    }
}