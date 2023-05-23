package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.LocationDB
import javax.inject.Inject

interface LocationDBMapper : Mapper<List<LocationDB>> {

    fun map(location: Map<String, String>, parent: ContentDB, dataBase: DataBase): List<LocationDB>

    class Base @Inject constructor(private val mapper: LocalNameDBMapper) : LocationDBMapper {
        override fun map(
            location: Map<String, String>, parent: ContentDB, dataBase: DataBase
        ): List<LocationDB> =
            location.map { mapper.map(it.key, it.value, dataBase, parent) }
    }
}

interface LocalNameDBMapper : Mapper<LocationDB> {
    fun map(language: String, name: String, database: DataBase, parent: ContentDB): LocationDB

    class Base @Inject constructor() : LocalNameDBMapper {

        override fun map(
            language: String, name: String, database: DataBase, parent: ContentDB
        ): LocationDB =
            database.createEmbeddedObject<LocationDB>(parent, ContentDB.FIELD_LOCATION).apply {
                this.language = language
                this.name = name
            }
    }
}