package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain

interface HorizonDomainMapper : Mapper<SunPosition> {
    fun map(sunrise: Long, sunset: Long): SunPosition
}