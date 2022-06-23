package com.github.skytoph.simpleweather.data.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.mapper.HorizonDataToDomainMapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

data class SunData(
    private val sunrise: Long,
    private val sunset: Long,
    private val currentTime: Long,
) :
    Mappable<WeatherDomain.Horizon, HorizonDataToDomainMapper> {

    override fun map(mapper: HorizonDataToDomainMapper): WeatherDomain.Horizon =
        mapper.map(sunrise, sunset, currentTime)
}
