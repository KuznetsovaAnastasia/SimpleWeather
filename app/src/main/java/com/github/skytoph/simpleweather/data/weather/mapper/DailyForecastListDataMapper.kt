package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.DailyForecastData
import javax.inject.Inject

interface DailyForecastListDataMapper : Mapper<List<DailyForecastData>> {

    fun <T : Mappable<DailyForecastData, DailyForecastDataMapper>> map(forecasts: List<T>): List<DailyForecastData>

    class Base @Inject constructor(private val mapper: DailyForecastDataMapper) :
        DailyForecastListDataMapper {

        override fun <T : Mappable<DailyForecastData, DailyForecastDataMapper>> map(forecasts: List<T>): List<DailyForecastData> {
            return forecasts.map { it.map(mapper) }
        }
    }
}