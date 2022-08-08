package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.squareup.moshi.Json

data class PlaceCloud(
    private val id: String = "",
    @field:Json(name = "name")
    private val name: String,
    @field:Json(name = "lat")
    private val lat: Double,
    @field:Json(name = "lon")
    private val lng: Double,
) {
    fun <T> map(mapper: PlaceCloudMapper<T>) = mapper.map("$lat,$lng", name, lat, lng)
}