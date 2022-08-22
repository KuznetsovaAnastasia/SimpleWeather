package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.data.favorites.FavoritesPrefCache
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesInteractor {

    fun getFavoriteIDs(): List<String>
    suspend fun saveFavorite(id: String)

    @ViewModelScoped
    class Base @Inject constructor(
        private val favoritesDataSource: FavoritesPrefCache,
        private val weatherRepository: WeatherRepository.Save,
    ) : FavoritesInteractor {

        override fun getFavoriteIDs(): List<String> =
            favoritesDataSource.read().ifEmpty { listOf("") }

        override suspend fun saveFavorite(id: String) {
            favoritesDataSource.save(id)
            weatherRepository.saveWeather()
        }
    }

    class Mock(private val favoritesDataSource: FavoritesPrefCache) : FavoritesInteractor {
        override fun getFavoriteIDs(): List<String> = favoritesDataSource.read()
        override suspend fun saveFavorite(id: String) = Unit
    }
}
