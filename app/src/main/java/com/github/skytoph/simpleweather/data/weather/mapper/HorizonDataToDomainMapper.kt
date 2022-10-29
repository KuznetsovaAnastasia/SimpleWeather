package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain
import javax.inject.Inject

interface HorizonDataToDomainMapper : Mapper<HorizonDomain> {
    fun map(
        sunrise: Int,
        sunset: Int,
        dayLength: Int,
        daylight: Int,
        sunPosition: Double,
    ): HorizonDomain

    class Base @Inject constructor() : HorizonDataToDomainMapper {

        override fun map(
            sunrise: Int,
            sunset: Int,
            dayLength: Int,
            daylight: Int,
            sunPosition: Double,
        ) = HorizonDomain(sunrise, sunset, dayLength, daylight, sunPosition)
    }
}