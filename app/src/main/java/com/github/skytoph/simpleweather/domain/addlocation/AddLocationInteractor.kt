package com.github.skytoph.simpleweather.domain.addlocation

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface AddLocationInteractor {
    suspend fun save(successCallback: suspend () -> Unit)

    @ViewModelScoped
    class Base @Inject constructor(
        private val repository: WeatherRepository.Save,
        private val errorHandler: ErrorHandler,
    ) : AddLocationInteractor {

        override suspend fun save(successCallback: suspend () -> Unit) {
            try {
                repository.checkReachingLimit(MAX_SAVED_FAVORITES)
                repository.saveWeather()
                successCallback()
            } catch (e: Exception) {
                errorHandler.handle(e)
            }
        }

        private companion object {
            const val MAX_SAVED_FAVORITES = 3
        }
    }
}