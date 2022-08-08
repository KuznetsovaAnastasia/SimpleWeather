package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.LocationData

interface LocationDataMapper : Mapper<LocationData> {
    fun map(
        id: String,
        lat: Double,
        lon: Double,
        locationName: String,
        favorite: Boolean = false,
    ): LocationData

    class Base : LocationDataMapper {

        override fun map(
            id: String,
            lat: Double,
            lon: Double,
            locationName: String,
            favorite: Boolean,
        ): LocationData =
            LocationData(id, lat, lon, locationName, favorite)
    }
}
