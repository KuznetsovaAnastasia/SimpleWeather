package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators

import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData

interface MappableToIndicator {
    fun map(mapper: IndicatorsDataMapper): IndicatorsData
}