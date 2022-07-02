package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.data.weather.model.LocationCloud
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("https://maps.googleapis.com/maps/api/geocode/json?result_type=locality&key=${BuildConfig.MAPS_API_KEY}")
    fun getLocation(@Query(value = "latlng", encoded = true) latlng: String): Call<LocationCloud>
}