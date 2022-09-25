package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.HourlyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.HourlyForecastDBMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class HourlyForecastData(
    private val time: Long,
    private val temp: Double,
    private val weatherId: Int,
    private val precipitationProb: Double,
) :
    Mappable<WeatherDomain.HourlyForecast, HourlyForecastDomainMapper>,
    MappableToDB.EmbeddedValid<HourlyForecastDB, WeatherDB, HourlyForecastDBMapper> {
    override fun map(mapper: HourlyForecastDomainMapper): WeatherDomain.HourlyForecast =
        mapper.map(time, temp, weatherId, precipitationProb)

    override fun map(
        mapper: HourlyForecastDBMapper,
        dataBase: DataBase,
        parent: WeatherDB,
    ): HourlyForecastDB = mapper.map(time, temp, weatherId, precipitationProb, parent, dataBase)
}