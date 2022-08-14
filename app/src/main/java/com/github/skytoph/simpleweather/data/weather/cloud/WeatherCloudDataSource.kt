package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherCloudDataSource {
    suspend fun getWeather(id: String, favorite: Boolean): WeatherData

    class Base @Inject constructor(
        private val forecastCloudDataSource: ForecastCloudDataSource,
        private val airQualityCloudDataSource: AirQualityCloudDataSource,
        private val placeCloudDataSource: PlaceCloudDataSource,
        private val cloudMapper: WeatherCloudToDataMapper,
        private val coordinatesMapper: IdMapper,
    ) : WeatherCloudDataSource {

        override suspend fun getWeather(id: String, favorite: Boolean): WeatherData {
            val coordinates = coordinatesMapper.map(id)
            val location = placeCloudDataSource.getPlace(coordinates)
            val forecast = forecastCloudDataSource.getForecast(coordinates)
            val airQuality = airQualityCloudDataSource.getAirQuality(coordinates)
            return cloudMapper.map(forecast, airQuality, location, favorite)
        }
    }
}