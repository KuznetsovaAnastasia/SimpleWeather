package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.presentation.search.SearchLoadingCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchLoadingCommunicationModule {

    @Binds
    abstract fun communication(communication: SearchLoadingCommunication.Base): SearchLoadingCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: SearchLoadingCommunication.Base): SearchLoadingCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: SearchLoadingCommunication.Base): SearchLoadingCommunication.Update
}