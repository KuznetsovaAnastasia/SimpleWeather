package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.weather.model.LocationCloud

interface LocationCloudDataSource {
    suspend fun getLocation(lat: Double, lng: Double): LocationCloud

    class Base(private val service: LocationService) : LocationCloudDataSource {

        override suspend fun getLocation(lat: Double, lng: Double): LocationCloud =
            service.getLocation("${lat},${lng}").execute().body()!!

    }

}
