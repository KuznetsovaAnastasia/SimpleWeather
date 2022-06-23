package com.github.skytoph.simpleweather.data

import com.github.skytoph.simpleweather.data.cloud.WeatherCloudDataSource
import com.github.skytoph.simpleweather.data.cloud.weather.mapper.WeatherServerToDataMapper

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lng: Double): WeatherData

    class Base(
        private val weatherCloudDataSource: WeatherCloudDataSource,
        private val mapper: WeatherServerToDataMapper
    ) : WeatherRepository {

        override suspend fun getWeather(lat: Double, lng: Double): WeatherData = try {
            val weatherServerModel = weatherCloudDataSource.getWeather(lat, lng)
            weatherServerModel.map(mapper)
        } catch (exception: Exception) {
            WeatherData.Fail(exception)
        }
    }
}