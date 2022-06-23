package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataToCurrentWeatherMapper
import com.github.skytoph.simpleweather.data.location.model.LocationData
import com.github.skytoph.simpleweather.domain.model.WeatherDomain.*

interface CurrentWeatherDataToDomainMapper : Mapper<CurrentWeather> {
    fun map(
        weatherId: Int,
        temperature: Double,
        lat: Double,
        lon: Double,
        timezone: String,
    ): CurrentWeather


    class Base(private val locationData: LocationData) : CurrentWeatherDataToDomainMapper {

        override fun map(
            weatherId: Int,
            temperature: Double,
            lat: Double,
            lon: Double,
            timezone: String,
        ): CurrentWeather {
            val mapper = object : LocationDataToCurrentWeatherMapper {
                override fun map(lat: Double, lng: Double, name: String) =
                    CurrentWeather(name, temperature, weatherId)
            }
            return locationData.map(mapper)
        }
    }
}
