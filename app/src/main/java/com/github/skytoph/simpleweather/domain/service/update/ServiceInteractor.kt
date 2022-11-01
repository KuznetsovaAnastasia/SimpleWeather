package com.github.skytoph.simpleweather.domain.service.update

import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject

interface ServiceInteractor {
    suspend fun updateForecasts()

    @ServiceScoped
    class Base @Inject constructor(
        private val repository: WeatherRepository.RefreshAll,
    ) : ServiceInteractor {

        override suspend fun updateForecasts() = repository.refreshAll()
    }
}