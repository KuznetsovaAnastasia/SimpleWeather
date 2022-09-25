package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.HourlyForecastDBMapper

data class HourlyForecastData(
    private val time: Long,
    private val temp: Double,
    private val weatherId: Int,
    private val precipitationProb: Double,
) :
//    Mappable<WeatherDomain.HourlyForecast, HourlyForecastDomainMapper>,
    MappableToDB.Embedded<HourlyForecastDB, HourlyForecastDBMapper> {
//    override fun map(mapper: HourlyForecastDomainMapper): WeatherDomain.HourlyForecast =
//        mapper.map(time, temp, weatherId, precipitationProb)

    override fun map(mapper: HourlyForecastDBMapper): HourlyForecastDB =
        mapper.map(time, temp, weatherId, precipitationProb)
}