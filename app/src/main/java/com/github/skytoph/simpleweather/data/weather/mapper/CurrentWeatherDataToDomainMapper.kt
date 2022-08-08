package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain.CurrentWeather

interface CurrentWeatherDataToDomainMapper : Mapper<CurrentWeather> {
    fun map(weatherId: Int, temperature: Double): CurrentWeather
}