package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.LocationDB

interface LocationDBMapper : Mapper<LocationDB> {

    fun map(id: String, lat: Double, lng: Double, name: String): LocationDB

    class Base : LocationDBMapper {
        override fun map(
            id: String,
            lat: Double,
            lng: Double,
            name: String,
        ) = LocationDB().apply {
            this.id = id
            this.lat = lat
            this.lon = lng
            this.location = name
        }
    }
}
