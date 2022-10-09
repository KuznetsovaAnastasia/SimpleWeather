package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.WarningDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import javax.inject.Inject

interface WarningDBMapper : Mapper<WarningDB> {
    fun map(
        name: String,
        startTime: Long,
        description: String,
        parent: WeatherDB,
        dataBase: DataBase,
    ): WarningDB

    class Base @Inject constructor() : WarningDBMapper {

        override fun map(
            name: String,
            startTime: Long,
            description: String,
            parent: WeatherDB,
            dataBase: DataBase,
        ) = dataBase.createEmbeddedObject<WarningDB>(parent, WeatherDB.FIELD_WARNINGS).apply {
            this.title = name
            this.expectedTime = startTime
            this.description = description
        }
    }
}
