package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.Timezone
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.IsDaytime
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain
import javax.inject.Inject

interface HourlyForecastDomainMapper : Mapper<HourlyDomain> {
    fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyDomain
}

interface HourlyForecastDataToDomainMapper : Mapper<HourlyDomain> {
    fun map(hourly: HourlyForecastData, horizon: IsDaytime, timezone: Timezone): HourlyDomain

    class Base @Inject constructor() : HourlyForecastDataToDomainMapper {

        override fun map(
            hourly: HourlyForecastData, horizon: IsDaytime, timezone: Timezone,
        ): HourlyDomain = hourly.map(object : HourlyForecastDomainMapper {
            override fun map(time: Long, temp: Double, weatherId: Int, pop: Double): HourlyDomain {
                val timeWithOffset = timezone.withOffset(time)
                val isDaytime = horizon.isDaytime(timeWithOffset)
                return HourlyDomain(timeWithOffset, temp, weatherId, pop, isDaytime)
            }
        })
    }
}