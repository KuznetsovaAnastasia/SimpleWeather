package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain

interface HourlyForecastDomainMapper : Mapper<HourlyDomain> {
    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyDomain
}