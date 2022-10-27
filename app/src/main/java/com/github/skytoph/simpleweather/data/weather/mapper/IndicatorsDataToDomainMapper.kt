package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.IndicatorsDomain

interface IndicatorsDataToDomainMapper : Mapper<IndicatorsDomain> {
    fun map(
        uvi: Double,
        precipitationProb: Double,
        airQuality: Int,
    ): IndicatorsDomain
}
