package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.WarningDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.AlertData
import javax.inject.Inject

interface WarningsDBMapper : Mapper<List<WarningDB>> {
    fun map(data: List<AlertData>, dataBase: DataBase, parent: WeatherDB): List<WarningDB>

    class Base @Inject constructor(private val mapper: WarningDBMapper) : WarningsDBMapper {

        override fun map(
            data: List<AlertData>,
            dataBase: DataBase,
            parent: WeatherDB,
        ): List<WarningDB> = data.map { it.map(mapper, dataBase, parent) }
    }
}