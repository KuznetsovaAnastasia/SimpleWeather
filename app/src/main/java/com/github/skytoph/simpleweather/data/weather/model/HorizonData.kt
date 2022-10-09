package com.github.skytoph.simpleweather.data.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.HorizonDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.HorizonDB
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

data class HorizonData(
    private val sunrise: Long,
    private val sunset: Long,
    private val currentTime: Long,
) : Mappable<WeatherDomain.Horizon, HorizonDataToDomainMapper>,
    MappableToDB.Embedded<HorizonDB, HorizonDBMapper> {

    override fun map(mapper: HorizonDataToDomainMapper): WeatherDomain.Horizon =
        mapper.map(sunrise, sunset, currentTime)

    override fun map(mapper: HorizonDBMapper): HorizonDB =
        mapper.map(sunrise, sunset, currentTime)
}
