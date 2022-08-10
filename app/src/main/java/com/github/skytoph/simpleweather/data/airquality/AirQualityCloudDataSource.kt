package com.github.skytoph.simpleweather.data.airquality

import com.github.skytoph.simpleweather.data.airquality.cloud.AirQualityCloud
import javax.inject.Inject

interface AirQualityCloudDataSource {
    suspend fun getAirQuality(coordinates: Pair<Double, Double>): AirQualityCloud

    class Base @Inject constructor(
        private val service: AirQualityService,
    ) : AirQualityCloudDataSource {

        override suspend fun getAirQuality(coordinates: Pair<Double, Double>): AirQualityCloud =
            service.getAirQuality(coordinates.first.toString(), coordinates.second.toString())
                .execute()
                .body()!!
    }
}
