package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain.CurrentWeather
import javax.inject.Inject

interface CurrentWeatherDataToDomainMapper : Mapper<CurrentWeather> {

    fun map(
        weatherId: Int,
        temperature: Double,
        location: String,
        favorite: Boolean,
    ): CurrentWeather

    class Base @Inject constructor() : CurrentWeatherDataToDomainMapper {

        override fun map(
            weatherId: Int,
            temperature: Double,
            location: String,
            favorite: Boolean,
        ) = CurrentWeather(location, temperature, weatherId, favorite)
    }
}