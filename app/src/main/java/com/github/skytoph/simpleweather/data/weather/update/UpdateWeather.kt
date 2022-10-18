package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.*

interface UpdateWeather {
    fun update(id: String, placeId: String, priority: Int, currentWeatherData: CurrentWeatherData): WeatherData
}