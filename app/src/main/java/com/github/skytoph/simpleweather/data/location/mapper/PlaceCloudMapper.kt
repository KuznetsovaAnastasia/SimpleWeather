package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface PlaceCloudMapper {

    fun map(placeId: String, name: String, lat: Double, lng: Double): WeatherData

}