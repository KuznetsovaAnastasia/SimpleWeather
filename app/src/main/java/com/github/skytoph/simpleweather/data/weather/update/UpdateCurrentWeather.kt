package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData

interface UpdateCurrentWeather {
    fun update(location: String, favorite: Boolean): CurrentWeatherData
}