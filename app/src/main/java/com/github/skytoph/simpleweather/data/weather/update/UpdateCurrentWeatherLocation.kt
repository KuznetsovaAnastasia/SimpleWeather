package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData

interface UpdateCurrentWeatherLocation {
    fun update(weatherId: Int, temperature: Double, favorite: Boolean): CurrentWeatherData
}