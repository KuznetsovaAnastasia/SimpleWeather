package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.CurrentCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.squareup.moshi.Json

data class CurrentWeatherCloud(
    @Json(name = "dt")
    private val dt: Long,

    @Json(name = "temp")
    private val temp: Double,

    @Json(name = "sunrise")
    private val sunrise: Long,

    @Json(name = "sunset")
    private val sunset: Long,

    @Json(name = "uvi")
    private val uvi: Double,

    @Json(name = "weather")
    private val weather: List<WeatherTypeCloud>,

    ) : Mappable<WeatherData, CurrentCloudToDataMapper> {

    override fun map(mapper: CurrentCloudToDataMapper): WeatherData =
        mapper.map(dt, temp, sunrise, sunset, uvi, weather[0].map())
}