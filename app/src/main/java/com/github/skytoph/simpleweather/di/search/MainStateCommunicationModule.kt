package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.presentation.main.communication.MainStateCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainStateCommunicationModule {

    @Binds
    abstract fun communication(communication: MainStateCommunication.Base): MainStateCommunication
}