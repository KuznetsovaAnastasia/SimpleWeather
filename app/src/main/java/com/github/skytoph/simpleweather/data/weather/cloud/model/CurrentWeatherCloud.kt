package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.CurrentCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.squareup.moshi.Json

class CurrentWeatherCloud(
    @field:Json(name = "dt")
    private val dt: Long,

    @field:Json(name = "temp")
    private val temp: Double,

    @field:Json(name = "sunrise")
    private val sunrise: Long,

    @field:Json(name = "sunset")
    private val sunset: Long,

    @field:Json(name = "uvi")
    private val uvi: Double,

    @field:Json(name = "weather")
    private val weather: List<WeatherTypeCloud>,

    ) : Mappable<WeatherData, CurrentCloudToDataMapper> {

    override fun map(mapper: CurrentCloudToDataMapper): WeatherData =
        mapper.map(dt, temp, sunrise, sunset, uvi, weather[0])
}