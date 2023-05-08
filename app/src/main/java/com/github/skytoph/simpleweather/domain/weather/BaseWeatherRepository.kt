package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.exception.FavoritesLimitException
import com.github.skytoph.simpleweather.data.weather.WeatherCache
import com.github.skytoph.simpleweather.data.weather.cache.WeatherCacheDataSource
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherCloudDataSource
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.domain.weather.mapper.CompareTimeWithCurrent
import com.github.skytoph.simpleweather.domain.weather.mapper.UpdatedLately
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseWeatherRepository @Inject constructor(
    private val cacheDataSource: WeatherCacheDataSource,
    private val cloudDataSource: WeatherCloudDataSource,
    private val cacheMapper: WeatherDBToDataMapper,
    private val cachedWeather: WeatherCache,
    private val timeMapper: CompareTimeWithCurrent,
) : WeatherRepository.Base {

    override fun cachedIDs(): List<String> =
        cacheDataSource.readAllIDs()

    override suspend fun getCachedWeather(id: String): WeatherData =
        cacheDataSource.read(id).map(cacheMapper)

    override suspend fun getCloudWeather(id: String): WeatherData =
        cloudDataSource.fetch(id).also { cachedWeather.cache(it) }

    override suspend fun updateCloudWeather(id: String): WeatherData =
        updateAndSave(getCachedWeather(id))

    override suspend fun updateLocationName(id: String): WeatherData =
        getCachedWeather(id).updateLocation(cloudDataSource).also { it.save(cacheDataSource) }

    override suspend fun checkReachingLimit(limit: Int) {
        if (cachedIDs().size >= limit) throw FavoritesLimitException(limit)
    }

    override suspend fun saveWeather() = cachedWeather.save(cacheDataSource)

    override suspend fun refreshAll(criteria: UpdatedLately) =
        cacheDataSource.readAll().map { it.map(cacheMapper) }.forEach { forecast ->
            if (!forecast.updatedLately(timeMapper, criteria)) updateAndSave(forecast)
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
}