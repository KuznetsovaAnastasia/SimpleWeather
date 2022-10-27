package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.SunCalculator
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain
import javax.inject.Inject

interface HorizonDataToDomainMapper : Mapper<HorizonDomain> {

    fun map(sunrise: Long, sunset: Long, currentTime: Long): HorizonDomain

    class Base @Inject constructor(
        private val sunCalculator: SunCalculator,
    ) : HorizonDataToDomainMapper {

        override fun map(sunrise: Long, sunset: Long, currentTime: Long): HorizonDomain =
            HorizonDomain(sunrise,
                sunset,
                sunCalculator.duration(sunrise, sunset),
                sunCalculator.remainingDaylight(sunrise, sunset, currentTime),
                sunCalculator.sunPosition(sunrise, sunset, currentTime))
    }
}