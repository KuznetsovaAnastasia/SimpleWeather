package com.github.skytoph.simpleweather.data.location.cloud

import com.google.gson.annotations.SerializedName

//"results": [
//        {
//            "address_components": [ ... ],
//            "formatted_address": "1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA",
//            "geometry": {
//                "location": {
//                    "lat": 37.4224428,
//                    "lng": -122.0842467
data class PlaceJson(
    @SerializedName("results")
    val results: List<ResultJson>
)

data class ResultJson(
    @SerializedName("geometry")
    val geometry: GeometryJson
)

data class GeometryJson(
    @SerializedName("location")
    val location: LocationJson,
)

data class LocationJson(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
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