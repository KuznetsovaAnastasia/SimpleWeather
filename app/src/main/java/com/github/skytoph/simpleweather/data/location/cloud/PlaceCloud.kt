package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.squareup.moshi.Json

data class PlaceCloud(
    @Json(name = "id")
    private val id: String,
    @Json(name = "name")
    private val name: String,
    @Json(name = "lat")
    private val lat: Double,
    @Json(name = "lon")
    private val lng: Double,
) : IdMapper.MappableToStringId, MappableTo<String>, Mappable<WeatherData, PlaceCloudMapper> {

    override fun map(mapper: PlaceCloudMapper) = mapper.map(id, name, lat, lng)
    override fun map(mapper: IdMapper) = mapper.map(lat, lng)
    override fun map(): String = name
}