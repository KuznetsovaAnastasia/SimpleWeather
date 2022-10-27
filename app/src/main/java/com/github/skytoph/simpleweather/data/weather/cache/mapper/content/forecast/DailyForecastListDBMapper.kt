package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import javax.inject.Inject

interface DailyForecastListDBMapper : Mapper<List<DailyForecastDB>> {

    fun map(
        forecasts: List<DailyForecastData>,
        dataBase: DataBase,
        parent: ForecastDB,
    ): List<DailyForecastDB>

    class Base @Inject constructor(private val mapper: DailyForecastDBMapper) :
        DailyForecastListDBMapper {

        override fun map(
            forecasts: List<DailyForecastData>,
            dataBase: DataBase,
            parent: ForecastDB,
        ): List<DailyForecastDB> = forecasts.map { it.map(mapper, dataBase, parent) }
    }
}