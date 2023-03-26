package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.squareup.moshi.Json

/*
"dt": 1664089200,
"temp": 286.3,
"feels_like": 285.58,
"pressure": 1010,
"humidity": 73,
"dew_point": 281.57,
"uvi": 0.78,
"clouds": 100,
"visibility": 10000,
"wind_speed": 7.23,
"wind_deg": 231,
"wind_gust": 9.22,
"weather": [
    {
    "id": 804,
    "main": "Clouds",
    "description": "overcast clouds",
    "icon": "04d"
    }
],
"pop": 0.64
 */

data class HourlyForecastCloud(
    @Json(name = "dt")
    private val dt: Long,
    @Json(name = "temp")
    private val temp: Double,
    @Json(name = "weather")
    private val weather: List<WeatherTypeCloud>,
    @Json(name = "pop")
    private val pop: Double,
    @Json(name = "uvi")
    private val uvi: Double,
) : Mappable<HourlyForecastData, HourlyForecastDataMapper>, MappableTo<Double> {

    override fun map(mapper: HourlyForecastDataMapper): HourlyForecastData =
        mapper.map(dt, temp, weather[0].map(), pop, uvi)

    override fun map(): Double = pop
}