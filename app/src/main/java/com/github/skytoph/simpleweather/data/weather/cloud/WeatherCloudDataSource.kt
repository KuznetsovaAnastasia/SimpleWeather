package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.core.data.UpdateItem
import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeatherMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

interface WeatherCloudDataSource : UpdateItem<WeatherData, IdentifierData> {
    suspend fun fetch(id: String): WeatherData

    class Base @Inject constructor(
        private val forecastCloudDataSource: ForecastCloudDataSource,
        private val airQualityCloudDataSource: AirQualityCloudDataSource,
        private val placeCloudDataSource: PlaceCloudDataSource,
        private val cloudMapper: WeatherCloudToDataMapper,
        private val updateMapper: UpdateWeatherMapper,
        private val idMapper: IdMapper,
    ) : WeatherCloudDataSource {

        override suspend fun fetch(id: String): WeatherData = coroutineScope {
            val location = placeCloudDataSource.place(id)
            val coordinates = location.mapToCoordinates(idMapper)
            val forecast = async { forecastCloudDataSource.getForecast(coordinates) }
            val airQuality = async { airQualityCloudDataSource.getAirQuality(coordinates) }
            cloudMapper.map(forecast.await(), airQuality.await(), location, false)
        }

        override suspend fun update(data: WeatherData): WeatherData = coroutineScope {
            val coordinates = data.mapToCoordinates(idMapper)
            val forecast = async { forecastCloudDataSource.getForecast(coordinates) }
            val airQuality = async { airQualityCloudDataSource.getAirQuality(coordinates) }
            updateMapper.update(data, forecast.await(), airQuality.await())
        }
    }
}