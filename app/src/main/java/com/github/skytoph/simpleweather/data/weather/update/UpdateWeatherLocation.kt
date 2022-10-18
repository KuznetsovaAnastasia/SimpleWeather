package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.*

interface UpdateWeatherLocation {
    fun update(
        id: String,
        placeId: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alertData: List<AlertData>,
        hourlyForecast: List<HourlyForecastData>,
        dailyForecast: List<DailyForecastData>,
        priority: Int,
    ): WeatherData
}