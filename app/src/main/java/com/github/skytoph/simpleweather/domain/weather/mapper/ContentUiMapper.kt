package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain
import com.github.skytoph.simpleweather.domain.weather.model.ForecastDomain
import com.github.skytoph.simpleweather.domain.weather.model.HorizonDomain
import com.github.skytoph.simpleweather.domain.weather.model.IndicatorsDomain
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi

interface ContentUiMapper: Mapper<WeatherUi>{
    fun map(
        currentWeather: CurrentWeatherDomain,
        indicators: IndicatorsDomain,
        horizon: HorizonDomain,
        forecast: ForecastDomain,
    ): WeatherUi
}