package com.github.skytoph.simpleweather.domain.weather

import android.util.Log
import com.github.skytoph.simpleweather.data.weather.WeatherCache
import com.github.skytoph.simpleweather.data.weather.cache.WeatherCacheDataSource
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherCloudDataSource
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.IndicatorsData
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherRepository {
    interface Save {
        suspend fun saveWeather()
        suspend fun updateCloudWeather(id: String): WeatherData
        suspend fun refreshAll()
    }

    interface Read {
        suspend fun getCachedWeather(id: String): WeatherData
        suspend fun getCloudWeather(id: String): WeatherData
    }

    interface Mutable : Read, Save

    @Singleton
    class Base @Inject constructor(
        private val cacheDataSource: WeatherCacheDataSource,
        private val cloudDataSource: WeatherCloudDataSource,
        private val cacheMapper: WeatherDBToDataMapper,
        private val cachedWeather: WeatherCache,
    ) : Mutable {

        override suspend fun getCachedWeather(id: String): WeatherData =
            getWeather { cacheDataSource.read(id).map(cacheMapper) }

        override suspend fun getCloudWeather(id: String): WeatherData =
            getWeather { cloudDataSource.fetch(id) }
                .also { cachedWeather.cache(it) }

        override suspend fun updateCloudWeather(id: String): WeatherData =
            getWeather { getCachedWeather(id).update(cloudDataSource) }
                .also { it.save(cacheDataSource) }

        override suspend fun saveWeather() {
            cachedWeather.save(cacheDataSource)
        }

        override suspend fun refreshAll() {
            cacheDataSource.readAll().map {
                it.map(cacheMapper).update(cloudDataSource)
                    .also { Log.e("ErrorTag", it.toString()) }
                    .save(cacheDataSource)
            }
        }

        private suspend fun getWeather(fetch: suspend () -> WeatherData) = try {
            fetch.invoke()
//                .also { cachedWeather.cache(it) }
        } catch (exception: Exception) {
            cachedWeather.clear()
            WeatherData.Fail(exception)
        }
    }

    class Mock : Mutable {
        override suspend fun saveWeather() = Unit

        override suspend fun getCachedWeather(id: String) =
            WeatherData.Info(
                id,
                CurrentWeatherData(10, 20.0, "city", true),
                IndicatorsData(1000000, 0.2, 0.5, 2),
                HorizonData(999999, 1000100, 1000000),
                emptyList())

        override suspend fun updateCloudWeather(id: String): WeatherData = getCachedWeather(id)

        override suspend fun getCloudWeather(id: String): WeatherData =
            getCachedWeather(id)

        override suspend fun refreshAll() {

        }
    }
}