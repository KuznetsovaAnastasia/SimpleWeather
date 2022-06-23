package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.AlertData
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

interface WarningsDataToDomainMapper : Mapper<List<WeatherDomain.Warning>> {

    fun map(warnings: List<AlertData>): List<WeatherDomain.Warning>

    class Base(private val mapper: WarningDataToDomainMapper) : WarningsDataToDomainMapper {

        override fun map(warnings: List<AlertData>): List<WeatherDomain.Warning> =
            warnings.map { it.map(mapper) }
    }
}
