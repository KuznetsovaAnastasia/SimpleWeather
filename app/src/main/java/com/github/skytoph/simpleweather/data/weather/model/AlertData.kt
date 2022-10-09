package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WarningDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WarningDB
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.mapper.WarningDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class AlertData(
    private val name: String,
    private val startTime: Long,
    private val precipitationProb: Double,
    private val description: String,
) : Mappable<WeatherDomain.Warning, WarningDataToDomainMapper>,
    MappableToDB.EmbeddedValid<WarningDB, WeatherDB, WarningDBMapper> {

    override fun map(mapper: WarningDataToDomainMapper): WeatherDomain.Warning =
        mapper.map(name, startTime, precipitationProb, description)

    override fun map(mapper: WarningDBMapper, dataBase: DataBase, parent: WeatherDB): WarningDB =
        mapper.map(name, startTime, description, parent, dataBase)
}
