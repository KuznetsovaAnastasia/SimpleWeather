package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import javax.inject.Inject

interface WarningDomainToUiMapper : Mapper<WeatherUiComponent.Warning> {
    fun map(
        event: String,
        startTime: Long,
        precipitationProb: Double,
        description: String,
    ): WeatherUiComponent.Warning

    class Base @Inject constructor(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : WarningDomainToUiMapper {
        override fun map(
            event: String,
            startTime: Long,
            precipitationProb: Double,
            description: String,
        ) = if (containsRain(event))
            WeatherUiComponent.WarningRain(
                event,
                probabilityFormatter.format(precipitationProb),
                timeFormatter.timeFull(startTime),
                R.drawable.weather_rain)
        else
            WeatherUiComponent.WarningBasic(
                event,
                description,
                timeFormatter.timeFull(startTime))

        private fun containsRain(event: String): Boolean {
            val lowercaseEvent = event.lowercase()
            return lowercaseEvent.contains("rain").or(lowercaseEvent.contains("thunderstorm"))
        }

    }
}
