package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.identifier.IdentifierDB
import com.github.skytoph.simpleweather.data.weather.cache.model.time.TimeDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.ContentDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.identifier.IdentifierDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.time.TimeDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import javax.inject.Inject

interface WeatherDBToDataMapper : Mapper<WeatherData> {
    fun map(id: String, identifier: IdentifierDB, time: TimeDB, content: ContentDB): WeatherData

    class Base @Inject constructor(
        private val timeMapper: TimeDBToDataMapper,
        private val contentMapper: ContentDBToDataMapper,
    ) : WeatherDBToDataMapper {

        override fun map(
            id: String, identifier: IdentifierDB, time: TimeDB, content: ContentDB,
        ): WeatherData {
            val identifierMapper = object : IdentifierDBToDataMapper {
                override fun map(lat: Double, lon: Double, priority: Int) =
                    IdentifierData(id, lat, lon, true, priority)
            }
            return WeatherData(
                identifier.map(identifierMapper),
                time.map(timeMapper),
                content.map(contentMapper)
            )
        }
    }
}