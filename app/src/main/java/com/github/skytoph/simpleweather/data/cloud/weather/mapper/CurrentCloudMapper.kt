package com.github.skytoph.simpleweather.data.cloud.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.WeatherData
import com.github.skytoph.simpleweather.data.cloud.weather.model.WeatherTypeCloud

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
