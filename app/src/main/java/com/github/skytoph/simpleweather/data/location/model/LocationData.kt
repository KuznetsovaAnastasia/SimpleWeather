package com.github.skytoph.simpleweather.data.location.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.WeatherData
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataToCurrentWeatherMapper
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

data class LocationData(
    private val lat: Double,
    private val lng: Double,
    private val name: String,
): Mappable<WeatherDomain.CurrentWeather, LocationDataToCurrentWeatherMapper>{
    override fun map(mapper: LocationDataToCurrentWeatherMapper) = mapper.map(lat, lng, name)
}
