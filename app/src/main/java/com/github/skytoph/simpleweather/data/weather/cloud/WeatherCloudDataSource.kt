package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.data.weather.model.WeatherCloud

interface WeatherCloudDataSource {
    suspend fun getWeather(lat: Double, lng: Double): WeatherCloud

    class Base(private val service: WeatherService) : WeatherCloudDataSource {
        override suspend fun getWeather(lat: Double, lng: Double): WeatherCloud =
            service.getWeather(lat.toString(), lng.toString()).execute().body()!!
    }

//    class Mock : WeatherCloudDataSource{
//        override suspend fun getWeather(): WeatherCloud =
//            listOf(
//            WeatherCloud(10.0,10.0, WeatherType(100, "rainy")),
//            WeatherCloud(30.0,80.0, WeatherType(2, "cloudy")),
//            WeatherCloud(204.0,15.0, WeatherType(66, "sunny"))
//        )
//    }
}