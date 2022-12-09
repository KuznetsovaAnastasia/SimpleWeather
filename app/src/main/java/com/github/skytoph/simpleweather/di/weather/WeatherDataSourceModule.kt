package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.location.CurrentLocation
import com.github.skytoph.simpleweather.data.location.cloud.FindPlace
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cache.WeatherCacheDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.ForecastCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherCloudDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDataSourceModule {

    @Binds
    @Singleton
    abstract fun cacheDataSource(source: WeatherCacheDataSource.Base): WeatherCacheDataSource

    @Binds
    @Singleton
    abstract fun weatherCloudDataSource(source: WeatherCloudDataSource.Base): WeatherCloudDataSource

    @Binds
    @Singleton
    abstract fun forecastCloudDataSource(source: ForecastCloudDataSource.Base): ForecastCloudDataSource

    @Binds
    @Singleton
    abstract fun airCloudDataSource(source: AirQualityCloudDataSource.Base): AirQualityCloudDataSource

    @Binds
    @Singleton
    abstract fun placeCloudDataSource(source: LocationCloudDataSource.Base): LocationCloudDataSource

    @Binds
    @Singleton
    abstract fun placeNameSearch(source: LocationCloudDataSource.Base): LocationCloudDataSource.PlaceNameSearch

    @Binds
    @Singleton
    abstract fun placeSearch(source: LocationCloudDataSource.Base): LocationCloudDataSource.PlaceSearch

    @Binds
    @Singleton
    abstract fun currentPlace(source: CurrentLocation.Base): CurrentLocation

    @Binds
    @Singleton
    abstract fun findPlace(source: FindPlace.Base): FindPlace
}