package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.model.LocationData

interface ToLocationDataMapper : Mapper<LocationData> {
    fun map(name: String): LocationData

}
