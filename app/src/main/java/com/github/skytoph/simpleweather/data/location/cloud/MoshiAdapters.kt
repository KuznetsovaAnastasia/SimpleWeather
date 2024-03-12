package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.ChoosePlace
import com.github.skytoph.simpleweather.data.location.cloud.model.*
import com.squareup.moshi.FromJson

class CoordinatesAdapter : ChoosePlace.Abstract() {
    @FromJson
    fun coordinatesFromJson(location: PlaceJson): PlaceCoordinatesCloud {
        val place: ResultJson? = choosePlace(location.results ?: emptyList()) { place, i ->
            place?.types?.contains(TARGET_TYPES[i].name.lowercase()) ?: false
        }
        val coordinates = place?.geometry?.location
        return PlaceCoordinatesCloud(
            placeId = place?.place_id ?: "",
            lat = coordinates?.lat ?: 0.0,
            lng = coordinates?.lng ?: 0.0,
            types = place?.types ?: emptyList()
        )
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
        return LocationCloud(location.name ?: "", names, location.lat, location.lon)
    }
}