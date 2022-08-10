package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.AlertData
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface WarningsDataToDomainMapper : Mapper<List<WeatherDomain.Warning>> {

    fun map(warnings: List<AlertData>): List<WeatherDomain.Warning>

    class Base @Inject constructor(private val mapper: WarningDataToDomainMapper) :
        WarningsDataToDomainMapper {

        override fun map(warnings: List<AlertData>): List<WeatherDomain.Warning> =
            warnings.map { it.map(mapper) }
    }
}
