package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.data.weather.cloud.model.WeatherCloud

interface WeatherCloudDataSource {
    suspend fun getWeather(coordinates: Pair<Double, Double>): WeatherCloud

    class Base(
        private val service: WeatherService,
    ) : WeatherCloudDataSource {

        override suspend fun getWeather(coordinates: Pair<Double, Double>): WeatherCloud =
            service.getWeather(coordinates.first.toString(), coordinates.second.toString())
                .execute().body()!!

    }
}