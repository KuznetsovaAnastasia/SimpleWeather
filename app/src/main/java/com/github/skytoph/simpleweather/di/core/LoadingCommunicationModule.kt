package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoadingCommunicationModule {

    @Binds
    abstract fun communication(communication: ProgressCommunication.Base): ProgressCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: ProgressCommunication.Base): ProgressCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: ProgressCommunication.Base): ProgressCommunication.Update

}