package com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast

import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators.MappableToIndicator
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData

interface MappableToCurrent {
    fun isCurrent(seconds: Long): Boolean
    fun map(mapper: CurrentForecastDataMapper): CurrentWeatherData
}

interface MappableToForecast : MappableToCurrent, MappableToIndicator