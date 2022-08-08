package com.github.skytoph.simpleweather.data.search.geocode

import com.github.skytoph.simpleweather.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PredictionService {

    @GET("https://api.openweathermap.org/geo/1.0/direct?limit=10&appid=${BuildConfig.WEATHER_API_KEY}")
    fun getPrediction(
        @Query(value = "q", encoded = true) query: String,
    ): Call<List<PredictionCloud>>

}