package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.domain.weather.model.DailyDomain
import javax.inject.Inject

interface DailyForecastListDomainMapper : Mapper<List<DailyDomain>> {

    fun map(forecasts: List<DailyForecastData>): List<DailyDomain>

    class Base @Inject constructor(private val mapper: DailyForecastDomainMapper) :
        DailyForecastListDomainMapper {

        override fun map(forecasts: List<DailyForecastData>): List<DailyDomain> =
            forecasts.map { it.map(mapper) }
    }
}