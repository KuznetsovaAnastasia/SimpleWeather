package com.github.skytoph.simpleweather.data.weather.model.identifier

import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier.IdentifierDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB

data class IdentifierData(
    private val id: String,
    private val placeId: String,
    private val favorite: Boolean,
    private val priority: Int = 0,
) : IdMapper.MappableToCoordinates,
    MappableToDB.Base<IdentifierDB, IdentifierDBMapper>,
    MappableTo<String> {

    override fun mapToCoordinates(mapper: IdMapper): Pair<Double, Double> = mapper.map(id)

    override fun map(mapper: IdentifierDBMapper, dataBase: DataBase): IdentifierDB =
        mapper.map(id, placeId, priority, dataBase)

    override fun map(): String = id
}