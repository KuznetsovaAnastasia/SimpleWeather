package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import javax.inject.Inject

interface SunPositionMapper : Mapper<SunPosition> {
    fun map(sunrise: Long, sunset: Long, timezone: Timezone): SunPosition

    class Base @Inject constructor(private val currentTime: CurrentTime) : SunPositionMapper {

        override fun map(sunrise: Long, sunset: Long, timezone: Timezone) =
            SunPosition(timezone.withOffset(sunrise),
                timezone.withOffset(sunset),
                timezone.withOffset(currentTime.inSeconds()))
    }
}