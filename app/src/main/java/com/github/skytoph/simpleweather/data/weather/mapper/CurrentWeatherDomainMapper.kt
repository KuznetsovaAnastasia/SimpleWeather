package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataToCurrentWeatherMapper
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.LocationData
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface CurrentWeatherDomainMapper : Mapper<WeatherDomain.CurrentWeather> {
    fun map(current: CurrentWeatherData, location: LocationData): WeatherDomain.CurrentWeather

    class Base @Inject constructor() : CurrentWeatherDomainMapper {

        override fun map(
            current: CurrentWeatherData,
            location: LocationData,
        ): WeatherDomain.CurrentWeather {
            val currentWeatherMapper = object : CurrentWeatherDataToDomainMapper {

                override fun map(
                    weatherId: Int,
                    temperature: Double,
                ): WeatherDomain.CurrentWeather {
                    val locationMapper = object : LocationDataToCurrentWeatherMapper {

                        override fun map(
                            lat: Double,
                            lng: Double,
                            name: String,
                            favorite: Boolean,
                        ) = WeatherDomain.CurrentWeather(name, temperature, weatherId, favorite)
                    }
                    return location.map(locationMapper)
                }
            }
            return current.map(currentWeatherMapper)
        }
    }
}