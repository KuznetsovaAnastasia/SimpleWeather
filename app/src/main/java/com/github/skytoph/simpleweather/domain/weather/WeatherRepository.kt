package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.WeatherCache
import com.github.skytoph.simpleweather.data.weather.cache.WeatherCacheDataSource
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherCloudDataSource
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherRepository {
    interface Write {
        suspend fun saveWeather()
        suspend fun refreshAll()
        suspend fun updateCloudWeather(id: String): WeatherData
        suspend fun delete(id: String)
    }

    interface Read {
        suspend fun cachedIDs(): List<String>
        suspend fun getCachedWeather(id: String): WeatherData
        suspend fun getCloudWeather(id: String): WeatherData
        suspend fun contains(id: String): Boolean
    }

    interface Mutable : Read, Write

    @Singleton
    class Base @Inject constructor(
        private val cacheDataSource: WeatherCacheDataSource,
        private val cloudDataSource: WeatherCloudDataSource,
        private val cacheMapper: WeatherDBToDataMapper,
        private val cachedWeather: WeatherCache,
    ) : Mutable {

        override suspend fun cachedIDs(): List<String> =
            cacheDataSource.readAllIDs()

        override suspend fun getCachedWeather(id: String): WeatherData =
            getWeather { cacheDataSource.read(id).map(cacheMapper) }

        override suspend fun getCloudWeather(id: String): WeatherData =
            getWeather { cloudDataSource.fetch(id) }
                .also { cachedWeather.cache(it) }

        override suspend fun updateCloudWeather(id: String): WeatherData =
            updateAndSave(getCachedWeather(id))

        override suspend fun saveWeather() =
            cachedWeather.save(cacheDataSource)

        override suspend fun refreshAll() = cacheDataSource.readAll().forEach {
            updateAndSave(it.map(cacheMapper))
        }

        private suspend fun updateAndSave(data: WeatherData): WeatherData =
            data.update(cloudDataSource).also { it.save(cacheDataSource) }

        override suspend fun delete(id: String) =
            cacheDataSource.remove(id)

        override suspend fun contains(id: String): Boolean = try {
            cacheDataSource.read(id)
            true
        } catch (exception: Exception) {
            false
        }

        private suspend fun getWeather(fetch: suspend () -> WeatherData) = try {
            fetch.invoke()
        } catch (exception: Exception) {
            cachedWeather.clear()
            WeatherData.Fail(exception)
        }
    }
}