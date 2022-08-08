package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface PlaceCloudMapper<T> {

    fun map(id: String, name: String, lat: Double, lng: Double): T

    interface ToCoordinates : PlaceCloudMapper<Pair<Double, Double>> {

        class Base : ToCoordinates {
            override fun map(id: String, name: String, lat: Double, lng: Double) = Pair(lat, lng)
        }
    }

    interface ToWeatherData : PlaceCloudMapper<WeatherData>
}