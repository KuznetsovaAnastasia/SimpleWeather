package com.github.skytoph.simpleweather.data.location.cloud

import com.squareup.moshi.FromJson

class CoordinatesAdapter {
    @FromJson
    fun coordinatesFromJson(location: PlaceJson): PlaceCoordinatesCloud {
        val coordinates = location.results.firstOrNull()?.geometry?.location
        return PlaceCoordinatesCloud(coordinates?.lat ?: 0.0, coordinates?.lng ?: 0.0)
    }
}