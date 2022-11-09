package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi

interface OutdatedWeatherUiMapper {
    fun map(location: String): WeatherUi.Outdated
}