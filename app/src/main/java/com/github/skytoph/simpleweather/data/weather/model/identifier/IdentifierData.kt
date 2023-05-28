package com.github.skytoph.simpleweather.data.weather.model.identifier

import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier.IdentifierDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB

data class IdentifierData(
    private val id: String,
    private val lat: Double,
    private val lon: Double,
    private val favorite: Boolean,
    private val priority: Int = 0,
) : IdMapper.MappableToCoordinates,
    MappableToDB.EmbeddedValid<IdentifierDB, WeatherDB, IdentifierDBMapper>,
    MappableTo<String> {

    override fun mapToCoordinates(mapper: IdMapper): Pair<Double, Double> =
        mapper.mapToCoordinates(lat, lon)

    override fun map(mapper: IdentifierDBMapper, dataBase: DataBase, parent: WeatherDB) =
        mapper.map(lat, lon, priority, dataBase, parent)

    override fun map(): String = id
}