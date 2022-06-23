package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.TimeFormatter
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent

interface WarningDomainToUiMapper : Mapper<WeatherUiComponent.Warning> {
    fun map(event: String, startTime: Long, precipitationProb: Double): WeatherUiComponent.Warning

    class Base(
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
