package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.HourlyForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.model.HourlyForecastData
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
    @field:Json(name = "dt")
    private val time: Long,
    @field:Json(name = "temp")
    private val temp: Double,
    @field:Json(name = "weather")
    private val weather: List<WeatherTypeCloud>,
    @field:Json(name = "pop")
    private val precipitationProb: Double,
) : Mappable<HourlyForecastData, HourlyForecastDataMapper>, MappableTo<Double> {

    override fun map(mapper: HourlyForecastDataMapper): HourlyForecastData =
        mapper.map(time, temp, weather[0].map(), precipitationProb)

    override fun map(): Double = precipitationProb
}