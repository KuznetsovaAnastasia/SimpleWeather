package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.WarningDB
import javax.inject.Inject

interface WarningDBMapper : Mapper<WarningDB> {
    fun map(
        name: String,
        startTime: Long,
        endTime: Long,
        description: String,
        parent: ForecastDB,
        dataBase: DataBase,
    ): WarningDB

    class Base @Inject constructor() : WarningDBMapper {

        override fun map(
            name: String,
            startTime: Long,
            endTime: Long,
            description: String,
            parent: ForecastDB,
            dataBase: DataBase,
        ) = dataBase.createEmbeddedObject<WarningDB>(parent, ForecastDB.FIELD_WARNINGS).apply {
            this.title = name
            this.startTime = startTime
            this.endTime = endTime
            this.description = description
        }
    }
}
