package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.AlertData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.github.skytoph.simpleweather.domain.weather.model.ForecastDomain

interface ForecastDomainMapper: Mapper<ForecastDomain> {
    fun map(
        alerts: List<AlertData>,
        hourlyForecast: List<HourlyForecastData>,
        dailyForecast: List<DailyForecastData>,
    ): ForecastDomain
}