package com.github.skytoph.simpleweather.domain.addlocation

import com.github.skytoph.simpleweather.core.domain.FunctionHandler
import com.github.skytoph.simpleweather.core.exception.DataIsNotCachedException
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface AddLocationInteractor {
    suspend fun isFavorite(placeId: String): Boolean
    suspend fun save(successCallback: suspend () -> Unit)

    @ViewModelScoped
    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Save,
        private val handler: FunctionHandler<Unit>,
    ) : AddLocationInteractor {

        override suspend fun isFavorite(placeId: String): Boolean = try {
            weatherRepository.getCachedWeather(placeId)
            true
        } catch (exception: DataIsNotCachedException) {
            false
        }

        override suspend fun save(successCallback: suspend () -> Unit) = handler.handle {
            weatherRepository.checkReachingLimit(MAX_SAVED_FAVORITES)
            weatherRepository.saveWeather()
            successCallback()
        }

        private companion object {
            const val MAX_SAVED_FAVORITES = 5
        }
    }
}