package com.github.skytoph.simpleweather.domain.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.weather.mapper.WeatherDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi

data class WeatherDomain(
    private val id: String,
    private val content: ContentDomain,
) : Mappable<WeatherUi, WeatherDomainToUiMapper> {

    override fun map(mapper: WeatherDomainToUiMapper): WeatherUi =
        mapper.map(id, content)
}