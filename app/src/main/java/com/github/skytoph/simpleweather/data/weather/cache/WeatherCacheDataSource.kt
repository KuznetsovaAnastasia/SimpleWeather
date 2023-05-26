package com.github.skytoph.simpleweather.data.weather.cache

import android.util.Log
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.data.RealmProvider
import com.github.skytoph.simpleweather.core.data.SaveItem
import com.github.skytoph.simpleweather.core.data.UpdateItemTime
import com.github.skytoph.simpleweather.core.exception.DataIsNotCachedException
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.model.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeatherMapper
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherCacheDataSource : SaveItem<WeatherData>,
    UpdateItemTime<WeatherData, IdentifierData> {

    fun read(id: String): WeatherDB
    fun readId(placeId: String): String
    fun readAll(): List<WeatherDB>
    fun readAllIDs(): List<String>
    fun remove(id: String)

    @Singleton
    class Base @Inject constructor(
        private val realmProvider: RealmProvider,
        private val mapper: WeatherDataDBMapper,
        private val updateMapper: UpdateWeatherMapper,
    ) : WeatherCacheDataSource {

        override fun read(id: String): WeatherDB = realmProvider.provide().use { realm ->
            return findRealmObject(realm, WeatherDB.FIELD_ID, id)?.let { realm.copyFromRealm(it) }
                ?: throw DataIsNotCachedException(id)
        }

        override fun readId(placeId: String): String = realmProvider.provide().use { realm ->
            return findRealmObject(realm, WeatherDB.FIELD_PLACE_ID, placeId)
                ?.let { realm.copyFromRealm(it).id } ?: throw DataIsNotCachedException(placeId)
        }

        override fun readAll(): List<WeatherDB> = realmProvider.provide().use { realm ->
            return realm.where(WeatherDB::class.java).findAll().let { realm.copyFromRealm(it) }
                ?.sortedBy { it.identifier!!.priority }
                ?: throw NoCachedDataException()
        }

        override fun readAllIDs(): List<String> = readAll()
            .also { it.forEach { Log.e("ErrorTag", it.toString()) } }.map { it.id }

        override fun remove(id: String) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                findRealmObject(realm, WeatherDB.FIELD_ID, id)?.deleteFromRealm()
            }
        }

        override fun saveOrUpdate(id: String, data: WeatherData) =
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    val weather = findRealmObject(realm, WeatherDB.FIELD_ID, id)
                    weather?.deleteFromRealm()
                    it.insert(data.map(mapper, DataBase(it)))
                }
            }

        override suspend fun updateTime(data: WeatherData, id: IdentifierData) =
            saveOrUpdate(id.map(), updateMapper.update(data))

        private fun findRealmObject(realm: Realm, field: String, value: String) =
            realm.where(WeatherDB::class.java).equalTo(field, value).findFirst()
    }
}