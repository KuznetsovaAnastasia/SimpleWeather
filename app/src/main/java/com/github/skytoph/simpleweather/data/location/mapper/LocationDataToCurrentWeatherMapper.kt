package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.WeatherData
import com.github.skytoph.simpleweather.data.model.CurrentWeatherData
import com.github.skytoph.simpleweather.domain.model.WeatherDomain
import com.github.skytoph.simpleweather.domain.model.WeatherDomain.CurrentWeather

interface LocationDataToCurrentWeatherMapper: Mapper<CurrentWeather> {
    fun map(lat: Double, lng: Double, name: String): CurrentWeather

}
