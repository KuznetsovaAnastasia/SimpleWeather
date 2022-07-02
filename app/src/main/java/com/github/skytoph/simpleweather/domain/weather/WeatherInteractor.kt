package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.airquality.AirRepository
import com.github.skytoph.simpleweather.data.location.LocationRepository
import com.github.skytoph.simpleweather.data.weather.WeatherRepository
import com.github.skytoph.simpleweather.data.mapper.WeatherDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

interface WeatherInteractor {
    suspend fun getWeather(lat: Double, lng: Double): WeatherDomain

    class Base(
        private val weatherRepository: WeatherRepository,
        private val airRepository: AirRepository,
        private val locationRepository: LocationRepository,
        private val toDomainMapper: WeatherDomainMapper
    ) : WeatherInteractor {

        override suspend fun getWeather(lat: Double, lng: Double): WeatherDomain {
            val weatherData = weatherRepository.getWeather(lat, lng)
            val airData = airRepository.getAirQuality(lat, lng)
            val locationData = locationRepository.getLocation(lat, lng)

            return toDomainMapper.map(weatherData, airData, locationData)
        }
    }
}
