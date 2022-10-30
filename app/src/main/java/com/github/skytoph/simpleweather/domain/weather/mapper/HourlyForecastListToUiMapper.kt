package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain
import com.github.skytoph.simpleweather.presentation.weather.model.ForecastUi
import javax.inject.Inject

interface HourlyForecastListToUiMapper : Mapper<List<ForecastUi.Hourly>> {

    fun map(forecasts: List<HourlyDomain>): List<ForecastUi.Hourly>

    class Base @Inject constructor(private val mapper: HourlyForecastToUiMapper) :
        HourlyForecastListToUiMapper {

        override fun map(forecasts: List<HourlyDomain>): List<ForecastUi.Hourly> =
            forecasts.map { it.map(mapper) }
    }
}