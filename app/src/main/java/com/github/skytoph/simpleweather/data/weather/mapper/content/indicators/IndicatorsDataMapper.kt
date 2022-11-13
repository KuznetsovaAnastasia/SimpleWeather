package com.github.skytoph.simpleweather.data.weather.mapper.content.indicators

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData

interface IndicatorsDataMapper : Mapper<IndicatorsData> {
    fun map(uvi: Double, precipitationProb: Double): IndicatorsData
}