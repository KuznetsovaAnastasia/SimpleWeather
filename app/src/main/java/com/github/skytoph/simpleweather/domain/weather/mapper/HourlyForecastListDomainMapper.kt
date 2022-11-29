package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.Timezone
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastFilter
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.IsDaytime
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain
import javax.inject.Inject

interface HourlyForecastListDomainMapper : Mapper<List<HourlyDomain>> {
    fun map(
        hourly: List<HourlyForecastData>, horizon: IsDaytime, timezone: Timezone,
    ): List<HourlyDomain>

    class Base @Inject constructor(
        private val hourlyMapper: HourlyForecastDataToDomainMapper,
        private val hourlyFilter: HourlyForecastFilter,
    ) : HourlyForecastListDomainMapper {

        override fun map(
            hourly: List<HourlyForecastData>, horizon: IsDaytime, timezone: Timezone,
        ): List<HourlyDomain> =
            hourlyFilter.filter(hourly).map { hourlyMapper.map(it, horizon, timezone) }
    }
}