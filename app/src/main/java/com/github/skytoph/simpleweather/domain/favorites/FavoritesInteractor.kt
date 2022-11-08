package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.data.pages.PagesDataSource
import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesInteractor {

    fun favoriteIDs(): List<String>
    fun saveRefreshLocationIntention()
    suspend fun removeFavorite(id: String)
    suspend fun refreshFavorites()
    fun savedPage(): Int
    fun savePage(position: Int)

    @ViewModelScoped
    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Mutable,
        private val errorHandler: ErrorHandler,
        private val refreshLocation: RefreshLocation.Mutable,
        private val pagesDataSource: PagesDataSource,
    ) : FavoritesInteractor {

        override fun favoriteIDs(): List<String> = weatherRepository.cachedIDs()

        override suspend fun removeFavorite(id: String) = weatherRepository.delete(id)

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

        override fun savedPage(): Int = pagesDataSource.read()

        override fun savePage(position: Int) = pagesDataSource.save(position)
    }
}