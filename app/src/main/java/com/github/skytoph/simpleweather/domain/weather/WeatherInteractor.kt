package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

interface WeatherInteractor {
    suspend fun getCachedWeather(id: String): WeatherDomain
    suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherDomain
    suspend fun cache()

    class Base(
        private val weatherRepository: WeatherRepository.Mutable,
        private val mapper: WeatherDataToDomainMapper,
    ) : WeatherInteractor {

        override suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherDomain =
            weatherRepository.getCloudWeather(id, favorite).map(mapper)

        override suspend fun getCachedWeather(id: String): WeatherDomain =
            weatherRepository.getCachedWeather(id).map(mapper)

        override suspend fun cache() = weatherRepository.saveWeather()
    }
}
