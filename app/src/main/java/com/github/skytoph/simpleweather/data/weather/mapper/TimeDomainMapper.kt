package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

interface TimeDomainMapper : Mapper<CurrentWeatherDomain> {
    fun map(time: Long): CurrentWeatherDomain
}