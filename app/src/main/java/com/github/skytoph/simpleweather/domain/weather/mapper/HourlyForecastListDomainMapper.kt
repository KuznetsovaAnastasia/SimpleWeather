package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastFilter
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain
import javax.inject.Inject

interface HourlyForecastListDomainMapper : Mapper<List<HourlyDomain>> {

    fun map(
        forecasts: List<HourlyForecastData>,
        timezoneOffset: Int,
    ): List<HourlyDomain>

    class Base @Inject constructor(
        private val mapper: HourlyForecastDomainMapper,
        private val filter: HourlyForecastFilter,
    ) : HourlyForecastListDomainMapper {

        override fun map(
            forecasts: List<HourlyForecastData>,
            timezoneOffset: Int,
        ): List<HourlyDomain> = filter.filter(forecasts, timezoneOffset).map { it.map(mapper) }
    }
}