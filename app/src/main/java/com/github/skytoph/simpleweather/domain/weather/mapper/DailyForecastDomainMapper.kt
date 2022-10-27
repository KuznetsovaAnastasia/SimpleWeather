package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.DailyDomain

interface DailyForecastDomainMapper : Mapper<DailyDomain> {

    fun map(
        time: Long,
        temp: Pair<Double, Double>,
        weatherId: Int,
        pop: Double,
    ): DailyDomain
}