package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData

interface MappableToForecast {
    fun isCurrent(seconds: Long): Boolean
    fun map(mapper: CurrentForecastDataMapper): CurrentWeatherData
}