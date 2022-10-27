package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastFilter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherFilterModule {

    @Binds
    abstract fun filter(filter: HourlyForecastFilter.Base): HourlyForecastFilter
}