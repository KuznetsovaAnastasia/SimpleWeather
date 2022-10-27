package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDBMapper : Mapper<List<HourlyForecastDB>> {

    fun map(
        forecasts: List<HourlyForecastData>,
        dataBase: DataBase,
        parent: ForecastDB,
    ): List<HourlyForecastDB>

    class Base @Inject constructor(private val mapper: HourlyForecastDBMapper) :
        HourlyForecastListDBMapper {

        override fun map(
            forecasts: List<HourlyForecastData>,
            dataBase: DataBase,
            parent: ForecastDB,
        ): List<HourlyForecastDB> = forecasts.map { it.map(mapper, dataBase, parent) }
    }
}