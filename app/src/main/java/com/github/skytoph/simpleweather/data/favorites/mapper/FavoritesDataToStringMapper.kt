package com.github.skytoph.simpleweather.data.favorites.mapper

import com.github.skytoph.simpleweather.data.favorites.FavoriteData

interface FavoritesDataToStringMapper {

    fun map(favorites: List<FavoriteData>): List<String>

    class Base(private val mapper: FavoriteDataToDomainMapper) : FavoritesDataToStringMapper {

        override fun map(favorites: List<FavoriteData>): List<String> = favorites.map { it.map() }
    }
}
