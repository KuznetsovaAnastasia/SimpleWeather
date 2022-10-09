package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDBMapper : Mapper<List<HourlyForecastDB>> {

    fun map(
        forecasts: List<HourlyForecastData>,
        dataBase: DataBase,
        parent: WeatherDB,
    ): List<HourlyForecastDB>

    class Base @Inject constructor(private val mapper: HourlyForecastDBMapper) :
        HourlyForecastListDBMapper {

        override fun map(
            forecasts: List<HourlyForecastData>,
            dataBase: DataBase,
            parent: WeatherDB,
        ): List<HourlyForecastDB> = forecasts.map { it.map(mapper, dataBase, parent) }
    }
}