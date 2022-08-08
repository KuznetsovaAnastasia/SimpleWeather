package com.github.skytoph.simpleweather.data.favorites.mapper

import com.github.skytoph.simpleweather.data.favorites.FavoriteData
import com.github.skytoph.simpleweather.data.favorites.cache.FavoriteDB

interface FavoritesDbToDataMapper {

    fun map(favorites: List<FavoriteDB>): List<FavoriteData>

    class Base(private val mapper: FavoriteDataMapper) : FavoritesDbToDataMapper {

        override fun map(favorites: List<FavoriteDB>) = favorites.map { it.map(mapper) }
    }
}