package com.github.skytoph.simpleweather.domain.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.TimeFormatter
import com.github.skytoph.simpleweather.domain.model.WeatherDomain
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent

interface WarningsDomainToUiMapper : Mapper<List<WeatherUiComponent.Warning>> {

    fun map(
        warnings: List<WeatherDomain.Warning>,
    ): List<WeatherUiComponent.Warning>

    class Base(private val mapper: WarningDomainToUiMapper) : WarningsDomainToUiMapper {

        override fun map(
            warnings: List<WeatherDomain.Warning>,
        ): List<WeatherUiComponent.Warning> = warnings.map { it.map(mapper) }

    }

}
