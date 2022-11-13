package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators

import com.github.skytoph.simpleweather.core.Mapper

interface IndicatorsDBMapper : Mapper<Unit> {
    fun map(airQuality: Int)
}
