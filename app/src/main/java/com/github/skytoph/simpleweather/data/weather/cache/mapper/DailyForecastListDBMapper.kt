package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.DailyForecastData
import javax.inject.Inject

interface DailyForecastListDBMapper : Mapper<List<DailyForecastDB>> {

    fun map(
        forecasts: List<DailyForecastData>,
        dataBase: DataBase,
        parent: WeatherDB,
    ): List<DailyForecastDB>

    class Base @Inject constructor(private val mapper: DailyForecastDBMapper) :
        DailyForecastListDBMapper {

        override fun map(
            forecasts: List<DailyForecastData>,
            dataBase: DataBase,
            parent: WeatherDB,
        ): List<DailyForecastDB> = forecasts.map { it.map(mapper, dataBase, parent) }
    }
}