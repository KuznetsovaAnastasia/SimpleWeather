package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.DailyForecastDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class DailyForecastData(
    private val time: Long,
    private val temp: Pair<Double, Double>,
    private val weatherId: Int,
    private val precipitationProb: Double,
) : Mappable<WeatherDomain.DailyForecast, DailyForecastDomainMapper>,
    MappableToDB.EmbeddedValid<DailyForecastDB, WeatherDB, DailyForecastDBMapper> {

    override fun map(mapper: DailyForecastDomainMapper): WeatherDomain.DailyForecast =
        mapper.map(time, temp, weatherId, precipitationProb)

    override fun map(
        mapper: DailyForecastDBMapper,
        dataBase: DataBase,
        parent: WeatherDB,
    ): DailyForecastDB =
        mapper.map(time, temp.first, temp.second, weatherId, precipitationProb, parent, dataBase)
}