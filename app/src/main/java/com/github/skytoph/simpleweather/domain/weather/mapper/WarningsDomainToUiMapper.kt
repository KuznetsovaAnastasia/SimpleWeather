package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import javax.inject.Inject

interface WarningsDomainToUiMapper : Mapper<List<WeatherUiComponent.Warning>> {

    fun map(warnings: List<WeatherDomain.Warning>): List<WeatherUiComponent.Warning>

    class Base @Inject constructor(private val mapper: WarningDomainToUiMapper) : WarningsDomainToUiMapper {

        override fun map(warnings: List<WeatherDomain.Warning>): List<WeatherUiComponent.Warning> =
            warnings.map { it.map(mapper) }

    }

}
