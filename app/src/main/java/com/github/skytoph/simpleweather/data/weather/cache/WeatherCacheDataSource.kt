package com.github.skytoph.simpleweather.data.weather.cache

import android.util.Log
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.RealmProvider
import com.github.skytoph.simpleweather.core.data.SaveItem
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherCacheDataSource : SaveItem<WeatherData> {

    fun read(id: String): WeatherDB
    fun readAll(): List<WeatherDB>
    fun remove(id: String, data: WeatherData)

    @Singleton
    class Base @Inject constructor(
        private val realmProvider: RealmProvider,
        private val mapper: WeatherDataDBMapper,
    ) : WeatherCacheDataSource {

        override fun read(id: String): WeatherDB = realmProvider.provide().use { realm ->
            val weather = findRealmObject(realm, id)
            Log.e("ErrorTag", weather.toString())
            return weather?.let { realm.copyFromRealm(it) } ?: throw NoCachedDataException()
        }

        override fun readAll(): List<WeatherDB> = realmProvider.provide().use { realm ->
            return realm.where(WeatherDB::class.java).findAll().let { realm.copyFromRealm(it) }
                ?: throw NoCachedDataException()
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
                    it.insert(data.map(mapper, DataBase(it)))
                }
            }

        private fun findRealmObject(realm: Realm, id: String) =
            realm.where(WeatherDB::class.java).equalTo("id", id).findFirst()
    }
}
