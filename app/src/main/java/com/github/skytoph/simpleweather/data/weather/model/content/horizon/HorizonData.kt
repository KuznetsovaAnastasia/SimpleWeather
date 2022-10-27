package com.github.skytoph.simpleweather.data.weather.model.content.horizon

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.horizon.HorizonDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.HorizonDB
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain

data class HorizonData(
    private val sunrise: Long,
    private val sunset: Long,
) : Mappable<HorizonDomain, HorizonDomainMapper>,
    MappableToDB.Embedded<HorizonDB, HorizonDBMapper> {

    override fun map(mapper: HorizonDomainMapper): HorizonDomain = mapper.map(sunrise, sunset)

    override fun map(mapper: HorizonDBMapper): HorizonDB = mapper.map(sunrise, sunset)
}
