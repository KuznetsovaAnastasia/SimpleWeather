package com.github.skytoph.simpleweather.domain.worker

import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

interface UpdateForecastInteractor {
    suspend fun updateForecasts()

    @Singleton
    class Base @Inject constructor(
        private val repository: WeatherRepository.RefreshAll,
    ) : UpdateForecastInteractor {

        override suspend fun updateForecasts() = repository.refreshAll()
    }
}