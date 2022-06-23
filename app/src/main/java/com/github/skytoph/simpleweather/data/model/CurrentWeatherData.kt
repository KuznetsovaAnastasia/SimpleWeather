package com.github.skytoph.simpleweather.data.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.mapper.CurrentWeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

data class CurrentWeatherData(
    private val weatherId: Int,
    private val temperature: Double,
    private val lat: Double,
    private val lon: Double,
    private val timezone: String
    //todo add city
) : Mappable<WeatherDomain.CurrentWeather, CurrentWeatherDataToDomainMapper> {

    override fun map(mapper: CurrentWeatherDataToDomainMapper): WeatherDomain.CurrentWeather =
        mapper.map(weatherId, temperature, lat, lon, timezone)
}
