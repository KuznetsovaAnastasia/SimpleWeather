package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherInteractorModule {

    @Binds
    abstract fun interactor(interactor: WeatherInteractor.Base): WeatherInteractor
}