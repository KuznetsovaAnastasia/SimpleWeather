package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface UpdateWeather {
    fun update(id: String, priority: Int, currentWeatherData: CurrentWeatherData): WeatherData
}