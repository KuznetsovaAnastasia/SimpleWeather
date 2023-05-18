package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

data class PlaceData(
    private val id: String,
    private val name: String,
    private val lat: Double,
    private val lng: Double,
) : IdMapper.MappableToStringId, MappableTo<String>, Mappable<WeatherData, PlaceCloudMapper> {

    override fun map(mapper: PlaceCloudMapper) = mapper.map(id, name, lat, lng)
    override fun map(mapper: IdMapper) = mapper.map(lat, lng)
    override fun map(): String = name
}