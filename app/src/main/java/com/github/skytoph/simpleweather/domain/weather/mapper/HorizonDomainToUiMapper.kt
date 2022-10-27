package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.HorizonUi
import javax.inject.Inject

interface HorizonDomainToUiMapper : Mapper<HorizonUi> {
    fun map(
        sunrise: Long,
        sunset: Long,
        dayLength: Long,
        remainingDaylight: Long,
        sunPosition: Double,
    ): HorizonUi

    class Base @Inject constructor(private val timeFormatter: TimeFormatter) :
        HorizonDomainToUiMapper {

        override fun map(
            sunrise: Long,
            sunset: Long,
            dayLength: Long,
            remainingDaylight: Long,
            sunPosition: Double,
        ) = HorizonUi(
            timeFormatter.timeFull(sunrise),
            timeFormatter.timeFull(sunset),
            timeFormatter.duration(dayLength),
            timeFormatter.duration(remainingDaylight),
            sunPosition
        )
    }
}
