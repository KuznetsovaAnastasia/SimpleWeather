package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.squareup.moshi.Json

data class PlaceCloud(
    @field:Json(name = "id")
    private val id: String,
    @field:Json(name = "name")
    private val name: String,
    @field:Json(name = "lat")
    private val lat: Double,
    @field:Json(name = "lon")
    private val lng: Double,
) {
    fun map(mapper: PlaceCloudMapper) = mapper.map(id, name, lat, lng)
    fun map(mapper: IdMapper) = mapper.map(lat, lng)
    fun map(): String = name
}