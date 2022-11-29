package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain
import javax.inject.Inject

interface SunPositionMapper : Mapper<SunPosition> {
    fun map(horizonData: HorizonData, timezone: Timezone): SunPosition

    class Base @Inject constructor(private val currentTime: CurrentTime) : SunPositionMapper {

        override fun map(horizonData: HorizonData, timezone: Timezone) =
            horizonData.map(object : HorizonDomainMapper {
                override fun map(sunrise: Long, sunset: Long) =
                    SunPosition(timezone.withOffset(sunrise),
                        timezone.withOffset(sunset),
                        timezone.withOffset(currentTime.inSeconds()))
            })
    }
}