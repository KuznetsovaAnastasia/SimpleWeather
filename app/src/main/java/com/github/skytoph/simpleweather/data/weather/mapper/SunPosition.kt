package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain

class SunPosition(sunrise: Long, sunset: Long, current: Long) :
    Mappable<HorizonDomain, HorizonDataToDomainMapper>,
    CalculateSunPosition.Abstract(sunrise, sunset, current) {

    override fun map(mapper: HorizonDataToDomainMapper): HorizonDomain =
        mapper.map(sunrise, sunset, dayLength(), remainingDaylight(), sunPosition())
}