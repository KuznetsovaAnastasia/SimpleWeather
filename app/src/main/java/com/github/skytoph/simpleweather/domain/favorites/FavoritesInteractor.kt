package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.core.exception.RefreshForecastsException
import com.github.skytoph.simpleweather.data.pages.PagesDataSource
import com.github.skytoph.simpleweather.domain.search.LocationsRepository
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesInteractor {

    fun favoriteIDs(): List<String>
    suspend fun removeFavorite(id: String)
    suspend fun saveCurrentLocation()
    fun savedPage(): Int
    fun savePage(position: Int)
    fun handleUpdatingError()

    @ViewModelScoped
    class Base @Inject constructor(
        private val repository: WeatherRepository.Mutable,
        private val locationRepository: LocationsRepository,
        private val errorHandler: ErrorHandler,
        private val pagesDataSource: PagesDataSource,
    ) : FavoritesInteractor {

        override fun favoriteIDs(): List<String> = repository.cachedIDs()

        override suspend fun removeFavorite(id: String) = repository.delete(id)

        override fun handleUpdatingError() = errorHandler.handle(RefreshForecastsException())

        override suspend fun saveCurrentLocation() {
            try {
                repository.getCloudWeather(locationRepository.currentPlaceId())
                repository.saveWeather()
            } catch (e: Exception) {
                errorHandler.handle(e)
            }
        }

        override fun savedPage(): Int = pagesDataSource.read()

        override fun savePage(position: Int) = pagesDataSource.save(position)
    }
}