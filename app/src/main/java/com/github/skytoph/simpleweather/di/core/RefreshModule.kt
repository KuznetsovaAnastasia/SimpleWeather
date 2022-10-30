package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.presentation.favorites.RefreshCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RefreshModule {

    @Binds
    abstract fun communicationObserve(communication: RefreshCommunication.Base): RefreshCommunication.Observe

    @Binds
    abstract fun communicationUpdate(communication: RefreshCommunication.Base): RefreshCommunication.Update

    @Binds
    abstract fun communication(communication: RefreshCommunication.Base): RefreshCommunication.Mutable
}