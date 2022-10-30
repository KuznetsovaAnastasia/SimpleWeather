package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.DailyDomain
import com.github.skytoph.simpleweather.presentation.weather.model.ForecastUi
import javax.inject.Inject

interface DailyForecastListToUiMapper : Mapper<List<ForecastUi.Daily>> {

    fun map(forecasts: List<DailyDomain>): List<ForecastUi.Daily>

    class Base @Inject constructor(private val mapper: DailyForecastToUiMapper) :
        DailyForecastListToUiMapper {

        override fun map(forecasts: List<DailyDomain>): List<ForecastUi.Daily> =
            forecasts.map { it.map(mapper) }
    }
}