package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain

interface CurrentWeatherDataToDomainMapper : Mapper<CurrentWeatherDomain> {
    fun map(weatherId: Int, temperature: Double, location: String): CurrentWeatherDomain
}