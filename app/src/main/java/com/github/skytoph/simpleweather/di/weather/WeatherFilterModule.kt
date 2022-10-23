package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.cache.WeatherForecastFilter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherFilterModule {

    @Binds
    abstract fun filter(filter: WeatherForecastFilter.Base): WeatherForecastFilter
}