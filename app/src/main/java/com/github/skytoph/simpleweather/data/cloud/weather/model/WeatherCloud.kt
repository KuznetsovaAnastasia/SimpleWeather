package com.github.skytoph.simpleweather.data.cloud.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.WeatherData
import com.github.skytoph.simpleweather.data.cloud.weather.mapper.WeatherServerToDataMapper
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

data class WeatherCloud(
    @field:Json(name="lat")
    private val lat: Double,

    @field:Json(name="lon")
    private val lon: Double,

    @field:Json(name="timezone")
    private val timezone: String,

    @field:Json(name="current")
    private val current: CurrentWeatherCloud,

//    @field:Json(name="temp")
//    private val temp: Double,
//
//    @field:Json(name="dt")
//    private val dt: Long,
//
//    @field:Json(name="sunrise")
//    private val sunrise: Long,
//
//    @field:Json(name="sunset")
//    private val sunset: Long,
//
//    @field:Json(name="uvi")
//    private val uvi: Double,

    @field:Json(name="weather")
    private val weather: List<WeatherTypeCloud>?,

    @field:Json(name="pop")
    private val precipitationProb: Double,

    @field:Json(name="hourly")
    private val hourly: List<HourlyForecastCloud>?,

    @field:Json(name="alerts")
    private val alerts: List<AlertCloud>?

) : Mappable<WeatherData, WeatherServerToDataMapper> {

    override fun map(mapper: WeatherServerToDataMapper): WeatherData = mapper.map(
        lat,
        lon,
        timezone,
        current,
        weather ?: emptyList(),
        hourly ?: emptyList(),
        alerts ?: emptyList()
    )

//    override fun map(mapper: ToWeatherDataMapper): WeatherData = mapper.map(
//        lat,
//        lon,
//        timezone,
//        temp,
//        dt,
//        sunrise,
//        sunset,
//        uvi,
//        weather ?: emptyList(),
//        precipitationProb,
//        alerts ?: emptyList()
//    )

    //    override fun map(mapper: WeatherServerToDataMapper): WeatherData = mapper.map(weather[0]) //todo make better

//    override fun map(mapper: WeatherServerToDataMapper): WeatherData =
//        mapper.map(lat, lon, timezone, current, hourlyForecast, alerts) //todo make better

}


