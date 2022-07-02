package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.WeatherTypeCloud

interface CurrentCloudMapper: Mapper<WeatherData> {
    fun map(
        dt: Long,
        temp: Double,
        sunrise: Long,
        sunset: Long,
        uvi: Double,
        weather: List<WeatherTypeCloud>
    ): WeatherData
}
