package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.data.weather.cloud.model.WeatherCloud
import javax.inject.Inject

interface WeatherCloudDataSource {
    suspend fun getWeather(coordinates: Pair<Double, Double>): WeatherCloud

    class Base @Inject constructor(private val service: WeatherService) : WeatherCloudDataSource {

        override suspend fun getWeather(coordinates: Pair<Double, Double>): WeatherCloud =
            service.getWeather(coordinates.first.toString(), coordinates.second.toString())
                .execute().body()!!

    }
}