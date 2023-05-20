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
        val localNames = location.namesLocal
        val names = mapOf(
            LocalNameJson.EN to localNames?.nameEn,
            LocalNameJson.UK to localNames?.nameUk
        )
        return LocationCloud(location.name, names, location.lat, location.lng)
    }
}