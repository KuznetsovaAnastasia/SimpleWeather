package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesInteractor {

    fun favoriteIDs(): List<String>
    suspend fun saveFavorite()
    suspend fun removeFavorite(id: String)
    suspend fun refreshFavorites()
    fun saveRefreshLocationIntention()

    @ViewModelScoped
    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Mutable,
        private val errorHandler: ErrorHandler,
        private val refreshLocation: RefreshLocation.Mutable,
    ) : FavoritesInteractor {

        override fun favoriteIDs(): List<String> =
            weatherRepository.cachedIDs()

        override suspend fun saveFavorite() =
            weatherRepository.saveWeather()

        override suspend fun removeFavorite(id: String) =
            weatherRepository.delete(id)

        override suspend fun refreshFavorites() {
            try {
                weatherRepository.refreshAll()
            } catch (error: Exception) {
                errorHandler.handle(error)
            }
        }

        override fun saveRefreshLocationIntention() = favoriteIDs().forEach {
            refreshLocation.saveIntention(it)
        }
    }
}