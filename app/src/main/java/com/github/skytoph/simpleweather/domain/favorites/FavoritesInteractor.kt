package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.data.pages.PagesDataSource
import com.github.skytoph.simpleweather.domain.search.LocationsRepository
import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import com.github.skytoph.simpleweather.domain.weather.mapper.DataUpdatedLatelyCriteria
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesInteractor {

    fun favoriteIDs(): List<String>
    fun saveRefreshLocationIntention()
    suspend fun removeFavorite(id: String)
    suspend fun refreshFavorites(firstUpdate: Boolean = false)
    suspend fun refreshLocations(ids: List<String>, applyChanges: suspend () -> Unit)
    suspend fun saveCurrentLocation()
    fun savedPage(): Int
    fun savePage(position: Int)

    @ViewModelScoped
    class Base @Inject constructor(
        private val repository: WeatherRepository.Mutable,
        private val locationRepository: LocationsRepository,
        private val errorHandler: ErrorHandler,
        private val refreshLocation: RefreshLocation.Mutable,
        private val pagesDataSource: PagesDataSource,
    ) : FavoritesInteractor {

        override fun favoriteIDs(): List<String> = repository.cachedIDs()

        override suspend fun removeFavorite(id: String) = repository.delete(id)

        override suspend fun refreshFavorites(firstUpdate: Boolean) {
            try {
                val dataUpdatedLatelyCriteria =
                    if (firstUpdate) DataUpdatedLatelyCriteria.DAYS else DataUpdatedLatelyCriteria.HOURS
                repository.refreshAll(dataUpdatedLatelyCriteria)
            } catch (error: Exception) {
                errorHandler.handle(error)
            }
        }

        override suspend fun refreshLocations(ids: List<String>, applyChanges: suspend () -> Unit) {
            try {
                var locationUpdated = false
                ids.forEach { id ->
                    if (refreshLocation.intentionSaved(id))
                        repository.updateLocationName(id).also {
                            it.saveStateRefreshed(refreshLocation)
                            locationUpdated = true
                        }
                }
                if (locationUpdated) applyChanges()
            } catch (exception: Exception) {
                errorHandler.handle(exception)
            }
        }

        override fun saveRefreshLocationIntention() = favoriteIDs().forEach {
            refreshLocation.saveIntention(it)
        }

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