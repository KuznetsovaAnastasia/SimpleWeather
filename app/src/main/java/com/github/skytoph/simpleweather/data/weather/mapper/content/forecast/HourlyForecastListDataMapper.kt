package com.github.skytoph.simpleweather.data.weather.mapper.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import javax.inject.Inject

interface HourlyForecastListDataMapper : Mapper<List<HourlyForecastData>> {

    fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(
        forecasts: List<T>,
    ): List<HourlyForecastData>

    class Base @Inject constructor(
        private val mapper: HourlyForecastDataMapper,
        private val filter: HourlyForecastFilter,
    ) : HourlyForecastListDataMapper {

        override fun <T : Mappable<HourlyForecastData, HourlyForecastDataMapper>> map(
            forecasts: List<T>,
        ): List<HourlyForecastData> = filter.filter(forecasts.map { it.map(mapper) })
    }
}
