package com.github.skytoph.simpleweather.data.favorites.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.favorites.cache.FavoriteDB

interface FavoriteDataToDbMapper : Mapper<FavoriteDB> {

    fun map(id: String, name: String, database: DataBase<FavoriteDB>): FavoriteDB

    class Base : FavoriteDataToDbMapper {
        override fun map(id: String, name: String, database: DataBase<FavoriteDB>) =
            database.createObject(id).apply {
                this.name = name
            }
    }
}
