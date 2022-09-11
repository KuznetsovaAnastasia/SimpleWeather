package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    abstract fun repository(repository: WeatherRepository.Base): WeatherRepository.Mutable

    @Binds
    abstract fun repositoryReadable(repository: WeatherRepository.Base): WeatherRepository.Read

    @Binds
    abstract fun repositoryWritable(repository: WeatherRepository.Base): WeatherRepository.Write
}