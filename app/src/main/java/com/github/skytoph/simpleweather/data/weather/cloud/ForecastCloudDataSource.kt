package com.github.skytoph.simpleweather.data.weather.cloud

import com.github.skytoph.simpleweather.data.weather.cloud.model.ForecastCloud
import javax.inject.Inject

interface ForecastCloudDataSource {
    suspend fun getForecast(coordinates: Pair<Double, Double>): ForecastCloud

    class Base @Inject constructor(private val service: ForecastService) : ForecastCloudDataSource {

        override suspend fun getForecast(coordinates: Pair<Double, Double>): ForecastCloud =
            service.getForecast(coordinates.first.toString(), coordinates.second.toString())
                .execute().body()!!

    }
}