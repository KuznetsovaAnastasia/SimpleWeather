package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataToCurrentWeatherMapper
import com.github.skytoph.simpleweather.data.weather.cache.LocationDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.LocationDBMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class LocationData(
    private val id: String,
    private val lat: Double,
    private val lng: Double,
    private val name: String,
    private val favorite: Boolean,
) : MappableTo<String>,
    Mappable<WeatherDomain.CurrentWeather, LocationDataToCurrentWeatherMapper>,
    MappableToDB.Embedded<LocationDB, LocationDBMapper> {

    override fun map(): String = id

    override fun map(mapper: LocationDataToCurrentWeatherMapper) =
        mapper.map(lat, lng, name, favorite)

    override fun map(mapper: LocationDBMapper): LocationDB =
        mapper.map(id, lat, lng, name)
}
