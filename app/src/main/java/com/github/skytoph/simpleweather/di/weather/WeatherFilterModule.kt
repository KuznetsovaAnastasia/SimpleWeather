package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastFilter
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.WarningsDataFilter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherFilterModule {

    @Binds
    abstract fun forecastFilter(filter: HourlyForecastFilter.Base): HourlyForecastFilter

    @Binds
    abstract fun warningFilter(filter: WarningsDataFilter.Base): WarningsDataFilter
}