package com.github.skytoph.simpleweather.data.weather.update

import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface UpdateForecastTime {
    fun update(timezoneOffset: Int, timezone: String): WeatherData
}
