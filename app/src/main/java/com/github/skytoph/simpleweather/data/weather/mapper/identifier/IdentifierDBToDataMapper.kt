package com.github.skytoph.simpleweather.data.weather.mapper.identifier

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData

interface IdentifierDBToDataMapper: Mapper<IdentifierData> {
    fun map(lat: Double, lon: Double, priority: Int): IdentifierData
}