package com.github.skytoph.simpleweather.data.airquality

import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.data.airquality.cloud.AirQualityCloud
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityService {

    @GET("https://api.openweathermap.org/data/2.5/air_pollution/forecast?appid=${BuildConfig.WEATHER_API_KEY}")
    fun getAirQuality(
        @Query(value = "lat", encoded = true) lat: String,
        @Query(value = "lon", encoded = true) lng: String,
    ): Call<AirQualityCloud>
}
