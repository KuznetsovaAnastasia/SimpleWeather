package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDataMapper : Mapper<List<HourlyForecastData>> {

    fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(forecasts: List<T>): List<HourlyForecastData>

    class Base @Inject constructor(private val mapper: HourlyForecastDataMapper) :
        HourlyForecastListDataMapper {

        override fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(forecasts: List<T>): List<HourlyForecastData> {
            return forecasts.subList(0, FORECAST_NUMBER).map { it.map(mapper) }
        }

        private companion object {
            const val FORECAST_NUMBER = 24
        }
    }
}
