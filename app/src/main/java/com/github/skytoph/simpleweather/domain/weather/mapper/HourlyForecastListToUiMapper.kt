package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent
import javax.inject.Inject

interface HourlyForecastListToUiMapper : Mapper<List<WeatherUiComponent.HourlyForecast>> {

    fun map(forecasts: List<WeatherDomain.HourlyForecast>): List<WeatherUiComponent.HourlyForecast>

    class Base @Inject constructor(private val mapper: HourlyForecastToUiMapper) :
        HourlyForecastListToUiMapper {

        override fun map(forecasts: List<WeatherDomain.HourlyForecast>): List<WeatherUiComponent.HourlyForecast> =
            forecasts.subList(0, FORECAST_NUMBER).map { it.map(mapper) }

        private companion object {
            const val FORECAST_NUMBER = 24
        }
    }
}
interface DailyForecastListToUiMapper : Mapper<List<WeatherUiComponent.DailyForecast>> {

    fun map(forecasts: List<WeatherDomain.DailyForecast>): List<WeatherUiComponent.DailyForecast>

    class Base @Inject constructor(private val mapper: DailyForecastToUiMapper) :
        DailyForecastListToUiMapper {

        override fun map(forecasts: List<WeatherDomain.DailyForecast>): List<WeatherUiComponent.DailyForecast> =
            forecasts.map { it.map(mapper) }
    }
}