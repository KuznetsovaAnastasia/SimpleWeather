package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain

interface HorizonDomainMapper : Mapper<HorizonDomain> {
    fun map(sunrise: Long, sunset: Long): HorizonDomain
}