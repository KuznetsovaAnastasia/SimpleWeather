package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.LocationData
import javax.inject.Inject

interface LocationDataMapper : Mapper<LocationData> {
    fun map(
        id: String,
        lat: Double,
        lon: Double,
        locationName: String,
        favorite: Boolean = false,
    ): LocationData

    class Base @Inject constructor() : LocationDataMapper {

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
