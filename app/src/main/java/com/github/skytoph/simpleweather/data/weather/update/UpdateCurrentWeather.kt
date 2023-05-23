package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData

interface UpdateCurrentWeather {
    fun update(location: Map<String, String>): CurrentWeatherData
}
