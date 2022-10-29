package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.HorizonUi
import javax.inject.Inject

interface HorizonDomainToUiMapper : Mapper<HorizonUi> {
    fun map(
        sunrise: Int,
        sunset: Int,
        dayLength: Int,
        remainingDaylight: Int,
        sunPosition: Double,
    ): HorizonUi

    class Base @Inject constructor(private val timeFormatter: TimeFormatter) :
        HorizonDomainToUiMapper {

        override fun map(
            sunrise: Int,
            sunset: Int,
            dayLength: Int,
            remainingDaylight: Int,
            sunPosition: Double,
        ) = HorizonUi(
            timeFormatter.timeFull(sunrise.toLong()),
            timeFormatter.timeFull(sunset.toLong()),
            timeFormatter.duration(dayLength.toLong()),
            timeFormatter.duration(remainingDaylight.toLong()),
            sunPosition
        )
    }
}
