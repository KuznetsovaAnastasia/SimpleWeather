package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface WeatherInteractor {
    suspend fun getCachedWeather(id: String): WeatherDomain
    suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherDomain
    suspend fun refreshAll()

    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Mutable,
        private val mapper: WeatherDataToDomainMapper,
    ) : WeatherInteractor {

        override suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherDomain =
            weatherRepository.run {
                if (favorite) updateCloudWeather(id)
                else getCloudWeather(id)
            }.map(mapper)

        override suspend fun getCachedWeather(id: String): WeatherDomain =
            weatherRepository.getCachedWeather(id).map(mapper)

        override suspend fun refreshAll() = weatherRepository.refreshAll()
    }
}
