package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.TimeFormatter
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent

interface HorizonDomainToUiMapper : Mapper<WeatherUiComponent.Horizon> {
    fun map(
        sunrise: Long,
        sunset: Long,
        dayLength: Long,
        remainingDaylight: Long,
        sunPosition: Double,
    ): WeatherUiComponent.Horizon

    class Base(private val timeFormatter: TimeFormatter) : HorizonDomainToUiMapper {

        override fun map(
            sunrise: Long,
            sunset: Long,
            dayLength: Long,
            remainingDaylight: Long,
            sunPosition: Double,
        ) = WeatherUiComponent.Horizon(
            timeFormatter.formatTime(sunrise),
            timeFormatter.formatTime(sunset),
            timeFormatter.formatDuration(dayLength),
            timeFormatter.formatDuration(remainingDaylight),
            sunPosition
        )
    }
}
