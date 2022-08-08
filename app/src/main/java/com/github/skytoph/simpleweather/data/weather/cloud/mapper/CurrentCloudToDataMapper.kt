package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cloud.model.WeatherTypeCloud
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface CurrentCloudToDataMapper : Mapper<WeatherData> {
    fun map(
        dt: Long,
        temp: Double,
        sunrise: Long,
        sunset: Long,
        uvi: Double,
        weather: WeatherTypeCloud,
    ): WeatherData
}
