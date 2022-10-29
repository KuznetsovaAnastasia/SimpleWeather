package com.github.skytoph.simpleweather.data.weather.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast.ForecastDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.ContentDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.mapper.ForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.ForecastDomain

data class ForecastData(
    private val warnings: List<WarningData>,
    private val hourlyForecast: List<HourlyForecastData>,
    private val dailyForecast: List<DailyForecastData>,
) : MappableToDB.EmbeddedValid<ForecastDB, ContentDB, ForecastDBMapper>,
    Mappable<ForecastDomain, ForecastDomainMapper> {

    override fun map(mapper: ForecastDBMapper, dataBase: DataBase, parent: ContentDB): ForecastDB =
        mapper.map(warnings, hourlyForecast, dailyForecast, parent, dataBase)

    override fun map(mapper: ForecastDomainMapper): ForecastDomain =
        mapper.map(warnings, hourlyForecast, dailyForecast)
}