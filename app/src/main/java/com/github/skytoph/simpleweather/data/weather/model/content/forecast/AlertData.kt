package com.github.skytoph.simpleweather.data.weather.model.content.forecast

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast.WarningDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.WarningDB
import com.github.skytoph.simpleweather.data.weather.mapper.WarningDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WarningDomain

data class AlertData(
    private val name: String,
    private val startTime: Long,
    private val description: String,
) : Mappable<WarningDomain, WarningDataToDomainMapper>,
    MappableToDB.EmbeddedValid<WarningDB, ForecastDB, WarningDBMapper> {

    override fun map(mapper: WarningDataToDomainMapper): WarningDomain =
        mapper.map(name, startTime, description)

    override fun map(mapper: WarningDBMapper, dataBase: DataBase, parent: ForecastDB): WarningDB =
        mapper.map(name, startTime, description, parent, dataBase)
}
