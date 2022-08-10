package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import javax.inject.Inject

interface WarningDomainToUiMapper : Mapper<WeatherUiComponent.Warning> {
    fun map(event: String, startTime: Long, precipitationProb: Double): WeatherUiComponent.Warning

    class Base @Inject constructor(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : WarningDomainToUiMapper {
        override fun map(
            event: String,
            startTime: Long,
            precipitationProb: Double,
        ) = WeatherUiComponent.Warning(
            event,
            timeFormatter.formatTime(startTime),
            probabilityFormatter.format(precipitationProb))
    }
}
