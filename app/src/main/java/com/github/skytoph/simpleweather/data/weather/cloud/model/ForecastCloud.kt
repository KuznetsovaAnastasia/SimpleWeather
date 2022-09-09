package com.github.skytoph.simpleweather.data.weather.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.squareup.moshi.Json

/*
  "lat": 39.31,
  "lon": -74.5,
  "timezone": "America/New_York",
  "timezone_offset": -18000,
  "current": {
    "dt": 1646318698,
    "sunrise": 1646306882,
    "sunset": 1646347929,
    "temp": 282.21,
    "feels_like": 278.41,
    "pressure": 1014,
    "humidity": 65,
    "dew_point": 275.99,
    "uvi": 2.55,
    "clouds": 40,
    "visibility": 10000,
    "wind_speed": 8.75,
    "wind_deg": 360,
    "wind_gust": 13.89,
    "weather": [
      {
        "id": 802,
        "main": "Clouds",
        "description": "scattered clouds",
        "icon": "03d"
      }
    ]
  },
 */

data class ForecastCloud(
    @field:Json(name = "lat")
    private val lat: Double,

    @field:Json(name = "lon")
    private val lon: Double,

    @field:Json(name = "current")
    private val current: CurrentWeatherCloud,

    @field:Json(name = "pop")
    private val precipitationProb: Double,

    @field:Json(name = "hourly")
    private val hourly: List<HourlyForecastCloud>?,

    @field:Json(name = "alerts")
    private val alerts: List<AlertCloud>?,
) : Mappable<WeatherData, WeatherCloudMapper> {

    override fun map(mapper: WeatherCloudMapper): WeatherData = mapper.map(
        current,
        hourly ?: emptyList(),
        alerts ?: emptyList()
    )
}