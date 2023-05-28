package com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB
import javax.inject.Inject

interface IdentifierDBMapper : Mapper<IdentifierDB> {
    fun map(
        lat: Double, lon: Double, priority: Int, database: DataBase, parent: WeatherDB
    ): IdentifierDB

    class Base @Inject constructor() : IdentifierDBMapper {

        override fun map(
            lat: Double, lon: Double, priority: Int, database: DataBase, parent: WeatherDB
        ) = database.createEmbeddedObject<IdentifierDB>(parent, WeatherDB.FIELD_IDENTIFIER).apply {
            this.lat = lat
            this.lon = lon
            this.priority =
                if (priority != 0) priority
                else database.findMax<IdentifierDB>(IdentifierDB.FIELD_PRIORITY) + 1
        }
    }
}