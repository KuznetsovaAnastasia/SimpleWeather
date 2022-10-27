package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WarningDomain

interface WarningDataToDomainMapper : Mapper<WarningDomain> {
    fun map(
        name: String,
        startTime: Long,
        description: String,
    ): WarningDomain
}