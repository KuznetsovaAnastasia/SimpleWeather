package com.github.skytoph.simpleweather.data.favorites.cache

import com.github.skytoph.simpleweather.core.data.CacheDataSource
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.Read
import com.github.skytoph.simpleweather.core.data.RealmProvider
import com.github.skytoph.simpleweather.data.favorites.FavoriteData
import com.github.skytoph.simpleweather.data.favorites.mapper.FavoriteDataToDbMapper
import io.realm.Realm

interface FavoritesCacheDataSource : CacheDataSource<FavoriteData>, Read<List<FavoriteDB>> {

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: FavoriteDataToDbMapper,
    ) :
        FavoritesCacheDataSource {

        override fun read(): List<FavoriteDB> = realmProvider.provide().use { realm ->
            val favoritesDB = realm.where(FavoriteDB::class.java).findAll() ?: emptyList()
            return realm.copyFromRealm(favoritesDB)
        }

        override fun save(data: FavoriteData) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                val database = FavoritesDatabase(it)
                data.map(mapper, database)
            }
        }

        private inner class FavoritesDatabase(realm: Realm) : DataBase.Abstract<FavoriteDB>(realm) {
            override fun dbClass() = FavoriteDB::class.java
        }
    }
}
