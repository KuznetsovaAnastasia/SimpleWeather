package com.github.skytoph.simpleweather.di.addlocation

import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoadingModule {
    
    @Binds
    abstract fun communication(communication: LoadingCommunication.Base): LoadingCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: LoadingCommunication.Base): LoadingCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: LoadingCommunication.Base): LoadingCommunication.Update
}