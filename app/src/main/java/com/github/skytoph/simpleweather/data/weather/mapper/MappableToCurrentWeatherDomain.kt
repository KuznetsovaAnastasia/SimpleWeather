package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

interface MappableToCurrentWeatherDomain<T : Mapper<CurrentWeatherDomain>> {
    fun map(mapper: T): CurrentWeatherDomain
}