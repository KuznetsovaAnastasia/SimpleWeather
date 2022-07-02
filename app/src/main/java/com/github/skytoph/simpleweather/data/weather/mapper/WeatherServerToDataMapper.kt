package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.WeatherData
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherTypeCloudMapper
import com.github.skytoph.simpleweather.data.weather.model.*
import com.github.skytoph.simpleweather.data.model.IndicatorsData
import com.github.skytoph.simpleweather.data.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.model.SunData

interface WeatherServerToDataMapper : Mapper<WeatherData> {
    fun map(
        lat: Double,
        lon: Double,
        timezone: String,
        current: CurrentWeatherCloud,
        weather: List<WeatherTypeCloud>,
        hourly: List<HourlyForecastCloud>,
        alerts: List<AlertCloud>,
    ): WeatherData

    class Base(
        private val weatherTypeMapper: WeatherTypeCloudMapper,
        private val alertsMapper: AlertsCloudMapper
    ) : WeatherServerToDataMapper {

        override fun map(
            lat: Double,
            lon: Double,
            timezone: String,
            current: CurrentWeatherCloud,
            weather: List<WeatherTypeCloud>,
            hourly: List<HourlyForecastCloud>,
            alerts: List<AlertCloud>,
        ): WeatherData {
            val currentMapper = object : CurrentCloudMapper {
                override fun map(
                    dt: Long,
                    temp: Double,
                    sunrise: Long,
                    sunset: Long,
                    uvi: Double,
                    weather: List<WeatherTypeCloud>,
                ): WeatherData {
                    val weatherId = weather[0].map(weatherTypeMapper).first
                    val pop = hourly[0].map()
                    return WeatherData.Base(
                        System.currentTimeMillis(),
                        CurrentWeatherData(weatherId, temp, lat, lon, timezone),
                        IndicatorsData(dt, temp, pop),
                        SunData(sunrise, sunset, dt),
                        alertsMapper.map(alerts, pop)
                    )
                }
            }

            return current.map(currentMapper)
        }
    }
}