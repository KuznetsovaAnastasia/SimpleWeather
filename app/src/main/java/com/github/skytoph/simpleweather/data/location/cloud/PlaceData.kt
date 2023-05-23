package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

data class PlaceData(
    private val id: String,
    private val names: Map<String, String>,
    private val lat: Double,
    private val lng: Double,
) : IdMapper.MappableToStringId, Mappable<WeatherData, PlaceCloudMapper> {

    override fun map(mapper: PlaceCloudMapper) = mapper.map(id, names, lat, lng)
    override fun map(mapper: IdMapper) = mapper.map(lat, lng)
}