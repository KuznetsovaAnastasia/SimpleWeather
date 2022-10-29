package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.WarningDB
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import javax.inject.Inject

interface WarningsDBMapper : Mapper<List<WarningDB>> {
    fun map(data: List<WarningData>, dataBase: DataBase, parent: ForecastDB): List<WarningDB>

    class Base @Inject constructor(private val mapper: WarningDBMapper) : WarningsDBMapper {

        override fun map(
            data: List<WarningData>,
            dataBase: DataBase,
            parent: ForecastDB,
        ): List<WarningDB> = data.map { it.map(mapper, dataBase, parent) }
    }
}