package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface PlaceCloudMapper : Mapper<WeatherData> {
    fun map(placeId: String, location: Map<String, String>, lat: Double, lng: Double): WeatherData
}