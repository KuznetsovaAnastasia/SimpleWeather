package com.github.skytoph.simpleweather.data.weather.cache

import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.RealmProvider
import com.github.skytoph.simpleweather.core.data.SaveItem
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import io.realm.Realm


interface WeatherCacheDataSource : SaveItem<WeatherData> {

    fun read(id: String): WeatherDB
    fun remove(id: String, data: WeatherData)

    class Base(private val realmProvider: RealmProvider, private val mapper: WeatherDataDBMapper) :
        WeatherCacheDataSource {

        override fun read(id: String): WeatherDB = realmProvider.provide().use { realm ->
            val weather = findRealmObject(realm, id)
            return weather?.let { realm.copyFromRealm(it) } ?: throw NoCachedDataException()
        }

        override fun remove(id: String, data: WeatherData) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    findRealmObject(realm, id)?.deleteFromRealm()
                }
            }

        override fun saveOrUpdate(id: String, data: WeatherData) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    val weather = findRealmObject(realm, id)
                    weather?.deleteFromRealm()
                    it.insert(data.map(mapper, WeatherDatabase(it)))
                }
            }

        private fun findRealmObject(realm: Realm, id: String) =
            realm.where(WeatherDB::class.java).equalTo("id", id).findFirst()

        private inner class WeatherDatabase(realm: Realm) : DataBase.Abstract<WeatherDB>(realm) {
            override fun dbClass() = WeatherDB::class.java
        }
    }
}
