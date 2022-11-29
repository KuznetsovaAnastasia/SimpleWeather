package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.IsDaytime
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import com.github.skytoph.simpleweather.domain.weather.model.CurrentWeatherDomain
import javax.inject.Inject

interface CurrentWeatherDomainMapper : Mapper<CurrentWeatherDomain> {
    fun map(weatherId: Int, temperature: Double, location: String): CurrentWeatherDomain
}

interface CurrentWeatherDataToDomainMapper : Mapper<CurrentWeatherDomain> {
    fun map(
        weather: CurrentWeatherData,
        horizon: IsDaytime,
        forecastTime: ForecastTimeData,
        timezone: Timezone,
    ): CurrentWeatherDomain

    class Base @Inject constructor(private val currentTime: CurrentTime) :
        CurrentWeatherDataToDomainMapper {

        override fun map(
            weather: CurrentWeatherData,
            horizon: IsDaytime,
            forecastTime: ForecastTimeData,
            timezone: Timezone,
        ): CurrentWeatherDomain = weather.map(object : CurrentWeatherDomainMapper {
            override fun map(
                weatherId: Int, temperature: Double, location: String,
            ) = forecastTime.map(object : TimeDomainMapper {
                override fun map(time: Long): CurrentWeatherDomain {
                    val isDaytime =
                        horizon.isDaytime(timezone.withOffset(currentTime.inSeconds()))
                    return CurrentWeatherDomain(location, temperature, weatherId, time, isDaytime)
                }
            })
        })
    }
}