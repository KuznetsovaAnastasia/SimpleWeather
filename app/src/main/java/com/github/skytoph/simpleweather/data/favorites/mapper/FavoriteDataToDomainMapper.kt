package com.github.skytoph.simpleweather.data.favorites.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.favorites.FavoriteDomain

interface FavoriteDataToDomainMapper : Mapper<FavoriteDomain> {
    fun map(id: String, name: String): FavoriteDomain

    class Base : FavoriteDataToDomainMapper {
        override fun map(id: String, name: String) = FavoriteDomain(id, name)
    }
}
