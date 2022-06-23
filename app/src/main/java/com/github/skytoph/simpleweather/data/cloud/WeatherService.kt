package com.github.skytoph.simpleweather.data.cloud

import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.data.cloud.weather.model.WeatherCloud
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("https://api.openweathermap.org/data/2.5/onecall?units=metric&appid=${BuildConfig.WEATHER_API_KEY}")
    fun getWeather(
        @Query(value = "lat", encoded = true) lat: String,
        @Query(value = "lon", encoded = true) lng: String,
    ): Call<WeatherCloud>
}