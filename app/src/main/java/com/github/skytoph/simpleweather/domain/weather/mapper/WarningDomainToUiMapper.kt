package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi
import javax.inject.Inject

interface WarningDomainToUiMapper : Mapper<WarningUi> {
    fun map(
        event: String,
        time: Long,
        started: Boolean,
        precipitationProb: Double,
        description: String,
    ): WarningUi

    class Base @Inject constructor(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : WarningDomainToUiMapper {
        override fun map(
            event: String,
            time: Long,
            started: Boolean,
            precipitationProb: Double,
            description: String,
        ): WarningUi {
            val timeTitle =
                if (started) R.string.expected_warning_end_time else R.string.expected_warning_start_time
            return if (containsRain(event)) {
                WarningUi.Rain(
                    event,
                    description,
                    timeFormatter.timeFull(time),
                    timeTitle,
                    R.drawable.weather_rain,
                    probabilityFormatter.format(precipitationProb))
            } else
                WarningUi.Basic(
                    event,
                    description,
                    timeFormatter.timeFull(time),
                    timeTitle)
        }

        private fun containsRain(event: String): Boolean {
            val lowercaseEvent = event.lowercase()
            return lowercaseEvent.contains("rain").or(lowercaseEvent.contains("thunderstorm"))
        }
    }
}
