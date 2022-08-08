package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain.CurrentWeather

interface LocationDataToCurrentWeatherMapper : Mapper<CurrentWeather> {
    fun map(lat: Double, lng: Double, name: String, favorite: Boolean): CurrentWeather

}
