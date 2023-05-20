package com.github.skytoph.simpleweather.data.location.cloud

import com.squareup.moshi.Json

//"results": [
//        {
//            "address_components": [ ... ],
//            "formatted_address": "1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA",
//            "geometry": {
//                "location": {
//                    "lat": 37.4224428,
//                    "lng": -122.0842467
data class PlaceJson(
    @Json(name = "results")
    val results: List<ResultJson>
)

data class ResultJson(
    @Json(name = "geometry")
    val geometry: GeometryJson
)

data class GeometryJson(
    @Json(name = "location")
    val location: LocationJson,
)

data class LocationJson(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lng: Double,
)

data class PlaceCoordinatesCloud(
    private val lat: Double,
    private val lng: Double,
) : IdMapper.MappableToStringId, IdMapper.MappableToCoordinates {

    override fun map(mapper: IdMapper): String = mapper.map(lat, lng)
    override fun mapToCoordinates(mapper: IdMapper): Pair<Double, Double> =
        mapper.mapToCoordinates(lat, lng)
}