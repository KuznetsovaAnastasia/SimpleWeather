package com.github.skytoph.simpleweather.domain.weather

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
    }

    interface Read {
        suspend fun getCachedWeather(id: String): WeatherData
        suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherData
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

        override suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherData =
            getWeather { cloudDataSource.getWeather(id, favorite) }.also { cachedWeather.cache(it) }

        override suspend fun saveWeather() {
            cachedWeather.save(cacheDataSource)
        }

        private suspend fun getWeather(fetch: suspend () -> WeatherData) = try {
            fetch.invoke()
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

        override suspend fun getCloudWeather(id: String, favorite: Boolean): WeatherData =
            getCachedWeather(id)
    }
}