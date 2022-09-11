package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.core.data.UpdateItem
import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeatherMapper
import javax.inject.Inject

interface WeatherCloudDataSource : UpdateItem<WeatherData> {
    suspend fun fetch(placeId: String): WeatherData

    class Base @Inject constructor(
        private val forecastCloudDataSource: ForecastCloudDataSource,
        private val airQualityCloudDataSource: AirQualityCloudDataSource,
        private val placeCloudDataSource: LocationCloudDataSource,
        private val cloudMapper: WeatherCloudToDataMapper,
        private val updateMapper: UpdateWeatherMapper,
        private val idMapper: IdMapper,
    ) : WeatherCloudDataSource {

        override suspend fun fetch(placeId: String): WeatherData {
            val location = placeCloudDataSource.place(placeId)
            val coordinates = idMapper.map(location.map(idMapper))
            val forecast = forecastCloudDataSource.getForecast(coordinates)
            val airQuality = airQualityCloudDataSource.getAirQuality(coordinates)
            return cloudMapper.map(forecast, airQuality, location, false)
        }

        override suspend fun update(data: WeatherData): WeatherData {
            val coordinates = data.map(idMapper)
            val forecast = forecastCloudDataSource.getForecast(coordinates)
            val airQuality = airQualityCloudDataSource.getAirQuality(coordinates)
            return updateMapper.update(data, forecast, airQuality)
        }
    }
}