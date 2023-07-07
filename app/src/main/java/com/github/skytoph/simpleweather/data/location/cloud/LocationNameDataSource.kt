package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.cloud.model.LocationCloud
import javax.inject.Inject

interface LocationNameDataSource {
    suspend fun find(coordinates: Pair<Double, Double>): LocationCloud

    class Base @Inject constructor(private val service: LocationService) :
        LocationNameDataSource {

        override suspend fun find(coordinates: Pair<Double, Double>) =
            service.getLocation(coordinates.first.toString(), coordinates.second.toString())
                .execute().body()!!.first()
    }
}