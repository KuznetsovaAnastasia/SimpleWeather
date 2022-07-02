package com.github.skytoph.simpleweather.data.airquality

import com.github.skytoph.simpleweather.data.airquality.model.AirQualityCloud

interface AirQualityCloudDataSource {
    suspend fun getAirQuality(lat: Double, lng: Double): AirQualityCloud

    class Base(private val service: AirQualityService) : AirQualityCloudDataSource {

        override suspend fun getAirQuality(lat: Double, lng: Double): AirQualityCloud =
            service.getAirQuality(lat.toString(), lng.toString()).execute().body()!!
    }
}
