package com.github.skytoph.simpleweather.data.weather.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast.DailyForecastDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.DailyForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.DailyDomain

data class DailyForecastData(
    private val time: Long,
    private val temp: Pair<Double, Double>,
    private val weatherId: Int,
    private val precipitationProb: Double,
) : Mappable<DailyDomain, DailyForecastDomainMapper>,
    MappableToDB.EmbeddedValid<DailyForecastDB, ForecastDB, DailyForecastDBMapper> {

    override fun map(mapper: DailyForecastDomainMapper): DailyDomain =
        mapper.map(time, temp, weatherId, precipitationProb)

    override fun map(
        mapper: DailyForecastDBMapper,
        dataBase: DataBase,
        parent: ForecastDB,
    ): DailyForecastDB =
        mapper.map(time, temp.first, temp.second, weatherId, precipitationProb, parent, dataBase)
}