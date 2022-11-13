package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.TimeProvider
import javax.inject.Inject

interface SunPositionMapper : Mapper<SunPosition> {
    fun map(sunrise: Long, sunset: Long, timezone: TimezoneOffset): SunPosition

    class Base @Inject constructor(private val timeProvider: TimeProvider) : SunPositionMapper {

        override fun map(sunrise: Long, sunset: Long, timezone: TimezoneOffset) =
            SunPosition(timezone.withOffset(sunrise),
                timezone.withOffset(sunset),
                timezone.withOffset(timeProvider.currentTimeInSeconds()))
    }
}