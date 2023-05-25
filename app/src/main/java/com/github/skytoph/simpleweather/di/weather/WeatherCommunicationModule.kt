package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.presentation.weather.communication.WeatherCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherCommunicationModule {

    @Binds
    abstract fun communication(communication: WeatherCommunication.Base): WeatherCommunication
}