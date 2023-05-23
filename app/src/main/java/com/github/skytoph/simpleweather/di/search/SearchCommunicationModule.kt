package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.presentation.search.SearchCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchCommunicationModule {

    @Binds
    abstract fun communication(communication: SearchCommunication.Base): SearchCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: SearchCommunication.Base): SearchCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: SearchCommunication.Base): SearchCommunication.Update
}
