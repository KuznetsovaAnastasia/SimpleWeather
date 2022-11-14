package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import com.github.skytoph.simpleweather.domain.weather.model.IndicatorsDomain
import javax.inject.Inject

interface IndicatorsDomainMapper : Mapper<IndicatorsDomain> {
    fun map(indicators: IndicatorsData, timezone: TimezoneOffset): IndicatorsDomain

    class Base @Inject constructor(
        private val timeProvider: TimeProvider,
    ) : IndicatorsDomainMapper {

        override fun map(indicators: IndicatorsData, timezone: TimezoneOffset): IndicatorsDomain =
            indicators.map(object : IndicatorsDataToDomainMapper {
                override fun map(uvi: Double, pop: Double, airQuality: Int): IndicatorsDomain =
                    IndicatorsDomain(timezone.withOffset(timeProvider.currentTimeInSeconds()),
                        uvi,
                        pop,
                        airQuality)
            })
    }
}

interface IndicatorsDataToDomainMapper : Mapper<IndicatorsDomain> {
    fun map(uvi: Double, pop: Double, airQuality: Int): IndicatorsDomain
}
