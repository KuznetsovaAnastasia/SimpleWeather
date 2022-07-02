package com.github.skytoph.simpleweather.data.airquality

import com.github.skytoph.simpleweather.data.airquality.mapper.ToAirQualityDataMapper
import com.github.skytoph.simpleweather.data.model.AirQualityData

interface AirRepository {
    suspend fun getAirQuality(lat: Double, lng: Double): AirQualityData

    class Base(
        private val airCloudDataSource: AirQualityCloudDataSource,
        private val mapper: ToAirQualityDataMapper
    ) : AirRepository {
        override suspend fun getAirQuality(lat: Double, lng: Double): AirQualityData = try {
            val airServerModel = airCloudDataSource.getAirQuality(lat, lng)
            airServerModel.map(mapper)
        } catch (exception: Exception) {
            AirQualityData(0, 0)
        }
    }

}