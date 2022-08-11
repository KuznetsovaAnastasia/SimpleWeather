package com.github.skytoph.simpleweather.data.favorites

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.MappableTo
import com.github.skytoph.simpleweather.core.MappableToDB
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.favorites.cache.FavoriteDB
import com.github.skytoph.simpleweather.data.favorites.mapper.FavoriteDataToDbMapper
import com.github.skytoph.simpleweather.data.favorites.mapper.FavoriteDataToDomainMapper
import com.github.skytoph.simpleweather.domain.favorites.FavoriteDomain

data class FavoriteData(private val id: String, private val name: String) :
    MappableToDB.Base<FavoriteDB, FavoriteDataToDbMapper>,
    Mappable<FavoriteDomain, FavoriteDataToDomainMapper>,
    MappableTo<String> {

    override fun map(mapper: FavoriteDataToDbMapper, dataBase: DataBase) =
        mapper.map(id, name, dataBase)

    override fun map(mapper: FavoriteDataToDomainMapper): FavoriteDomain = mapper.map(id, name)

    override fun map(): String = id
}