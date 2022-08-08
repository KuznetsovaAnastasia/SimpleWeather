package com.github.skytoph.simpleweather.data.favorites.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.favorites.FavoriteData

interface FavoriteDataMapper : Mapper<FavoriteData> {

    fun map(id: String, name: String): FavoriteData

    class Base : FavoriteDataMapper {

        override fun map(id: String, name: String) = FavoriteData(id, name)
    }
}
