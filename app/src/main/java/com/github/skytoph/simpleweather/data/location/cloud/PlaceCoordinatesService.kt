package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceCoordinatesService {

    @GET(value = "https://maps.googleapis.com/maps/api/geocode/json?key=${BuildConfig.GEOCODING_API_KEY}")
    fun getPlace(
        @Query(value = "place_id", encoded = true) placeId: String
    ): Call<PlaceCoordinatesCloud>
}