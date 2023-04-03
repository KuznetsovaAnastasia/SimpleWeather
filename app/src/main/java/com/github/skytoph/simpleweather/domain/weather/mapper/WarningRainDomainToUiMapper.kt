package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi
import javax.inject.Inject

interface WarningRainDomainToUiMapper : Mapper<WarningUi.Rain> {
    fun map(
        event: String,
        time: Long,
        started: Boolean,
        precipitationProb: Double,
        description: String,
    ): WarningUi.Rain

    class Base @Inject constructor(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : WarningRainDomainToUiMapper {
        override fun map(
            event: String,
            time: Long,
            started: Boolean,
            precipitationProb: Double,
            description: String,
        ): WarningUi.Rain = WarningUi.Rain(
            event,
            description,
            timeFormatter.timeFull(time),
            if (started) R.string.expected_warning_end_time else R.string.expected_warning_start_time,
            R.drawable.weather_rain,
            probabilityFormatter.format(precipitationProb)
        )
    }
}