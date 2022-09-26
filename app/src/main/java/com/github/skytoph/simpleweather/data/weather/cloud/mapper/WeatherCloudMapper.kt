package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cloud.model.*
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface WeatherCloudMapper : Mapper<WeatherData> {
    fun map(
        current: CurrentWeatherCloud,
        hourly: List<HourlyForecastCloud>,
        daily: List<DailyForecastCloud>,
        alerts: List<AlertCloud>,
    ): WeatherData
}