package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("https://api.openweathermap.org/geo/1.0/reverse?limit=1&appid=${BuildConfig.WEATHER_API_KEY}")
    fun getLocation(
        @Query(value = "lat", encoded = true) lat: String,
        @Query(value = "lon", encoded = true) lng: String,
    ): Call<List<LocationCloud>>
}
