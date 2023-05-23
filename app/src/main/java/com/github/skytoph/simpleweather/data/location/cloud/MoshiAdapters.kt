package com.github.skytoph.simpleweather.data.location.cloud

import com.squareup.moshi.FromJson

class CoordinatesAdapter {
    @FromJson
    fun coordinatesFromJson(location: PlaceJson): PlaceCoordinatesCloud {
        val coordinates = location.results.firstOrNull()?.geometry?.location
        return PlaceCoordinatesCloud(coordinates?.lat ?: 0.0, coordinates?.lng ?: 0.0)
    }
}

class LocationAdapter {
    @FromJson
    fun locationFromJson(location: LocationNamesJson): LocationCloud {
        val localNames = location.local_names
        val names = mapOf(
            LocalNameJson.EN to localNames?.en,
            LocalNameJson.UK to localNames?.uk
        )
        return LocationCloud(location.name, names, location.lat, location.lon)
    }
}