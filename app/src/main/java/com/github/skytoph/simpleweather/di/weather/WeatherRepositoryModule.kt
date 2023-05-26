package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.domain.weather.BaseWeatherRepository
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import com.github.skytoph.simpleweather.domain.weather.mapper.CompareTimeWithCurrent
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    abstract fun repository(repository: BaseWeatherRepository): WeatherRepository.Base

    @Binds
    abstract fun repositoryMutable(repository: BaseWeatherRepository): WeatherRepository.Mutable

    @Binds
    abstract fun repositoryUpdate(repository: BaseWeatherRepository): WeatherRepository.Update

    @Binds
    abstract fun repositoryRefresh(repository: BaseWeatherRepository): WeatherRepository.RefreshAll

    @Binds
    abstract fun repositorySave(repository: BaseWeatherRepository): WeatherRepository.Save

    @Binds
    abstract fun compareTime(compare: CompareTimeWithCurrent.Base): CompareTimeWithCurrent
}