package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.airquality.AirQualityService
import com.github.skytoph.simpleweather.data.location.cloud.LocationService
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object WeatherCloudModule {

    @Provides
    fun weatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)

    @Provides
    fun airQualityService(retrofit: Retrofit): AirQualityService =
        retrofit.create(AirQualityService::class.java)

    @Provides
    fun locationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)
}