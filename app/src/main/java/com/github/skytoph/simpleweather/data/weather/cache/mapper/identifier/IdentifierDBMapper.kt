package com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB
import javax.inject.Inject

interface IdentifierDBMapper : Mapper<IdentifierDB> {
    fun map(id: String, placeId: String, priority: Int, database: DataBase): IdentifierDB

    class Base @Inject constructor() : IdentifierDBMapper {

        override fun map(id: String, placeId: String, priority: Int, database: DataBase): IdentifierDB =
            IdentifierDB().apply {
                this.placeId = placeId
                this.priority =
                    if (priority != 0) priority
                    else database.findMax<IdentifierDB>(IdentifierDB.FIELD_PRIORITY) + 1
            }
    }
}