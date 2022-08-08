package com.github.skytoph.simpleweather.domain.favorites

import com.github.skytoph.simpleweather.data.favorites.FavoritesPrefCache
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository

interface FavoritesInteractor {

    fun getFavoriteIDs(): List<String>
    suspend fun saveFavorite(id: String)

    class Base(
        private val favoritesDataSource: FavoritesPrefCache,
        private val weatherRepository: WeatherRepository.Save,
    ) : FavoritesInteractor {

        override fun getFavoriteIDs(): List<String> = favoritesDataSource.read()

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
