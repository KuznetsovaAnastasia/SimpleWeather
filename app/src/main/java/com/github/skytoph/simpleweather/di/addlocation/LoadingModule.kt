package com.github.skytoph.simpleweather.di.addlocation

import com.github.skytoph.simpleweather.presentation.addlocation.communication.WeatherLoadingCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoadingModule {

    @Binds
    abstract fun communication(communication: WeatherLoadingCommunication.Base): WeatherLoadingCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: WeatherLoadingCommunication.Base): WeatherLoadingCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: WeatherLoadingCommunication.Base): WeatherLoadingCommunication.Update
}