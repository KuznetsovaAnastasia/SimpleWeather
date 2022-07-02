package com.github.skytoph.simpleweather.data.location

import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.location.mapper.ToLocationDataMapper
import com.github.skytoph.simpleweather.data.location.model.LocationData

interface LocationRepository {
    suspend fun getLocation(lat: Double, lng: Double): LocationData

    class Base(
        private val locationDataSource: LocationCloudDataSource,
    ) : LocationRepository {

        override suspend fun getLocation(lat: Double, lng: Double): LocationData = try {
            val mapper = object : ToLocationDataMapper{
                override fun map(name: String) = LocationData(lat, lng, name)
            }
            locationDataSource.getLocation(lat, lng).map(mapper)
        } catch (e: Exception) {
            LocationData(0.0, 0.0, "")
        }
    }
}
