package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import com.github.skytoph.simpleweather.domain.weather.model.ContentDomain

interface ContentDataToDomainMapper: Mapper<ContentDomain> {
    fun map(
        currentWeather: CurrentWeatherData,
        indicators: IndicatorsData,
        horizon: HorizonData,
        forecast: ForecastData,
    ): ContentDomain
}