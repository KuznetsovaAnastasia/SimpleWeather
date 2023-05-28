package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.ContentDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier.IdentifierDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.time.TimeDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import javax.inject.Inject

interface WeatherDataDBMapper : Mapper<WeatherDB> {
    fun map(
        identifier: IdentifierData,
        time: ForecastTimeData,
        content: ContentData,
        dataBase: DataBase,
    ): WeatherDB

    class Base @Inject constructor(
        private val timeMapper: TimeDBMapper,
        private val contentMapper: ContentDBMapper,
        private val identifierMapper: IdentifierDBMapper,
    ) : WeatherDataDBMapper {

        override fun map(
            identifier: IdentifierData,
            time: ForecastTimeData,
            content: ContentData,
            dataBase: DataBase,
        ): WeatherDB = dataBase.createObject<WeatherDB>(identifier.map()).apply {
            this.time = time.map(timeMapper)
            identifier.map(identifierMapper, dataBase, this)
            content.map(contentMapper, dataBase, this)
        }
    }
}
