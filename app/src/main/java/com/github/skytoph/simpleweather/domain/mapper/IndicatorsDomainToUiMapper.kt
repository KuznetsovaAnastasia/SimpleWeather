package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.TimeFormatter
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent
import kotlin.math.roundToInt

interface IndicatorsDomainToUiMapper : Mapper<WeatherUiComponent.Indicator> {
    fun map(
        time: Long,
        uvi: Double,
        precipitationProb: Double,
        airQualityIndex: Int,
    ): WeatherUiComponent.Indicator

    class Base(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : IndicatorsDomainToUiMapper {

        override fun map(
            time: Long,
            uvi: Double,
            precipitationProb: Double,
            airQualityIndex: Int,
        ) = WeatherUiComponent.Indicator(
            timeFormatter.formatTime(time),
            uvi.roundToInt().toString(),
            probabilityFormatter.format(precipitationProb),
            airQualityIndex.toString()
        )
    }
}
