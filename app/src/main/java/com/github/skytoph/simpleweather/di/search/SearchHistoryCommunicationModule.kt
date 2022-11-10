package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.presentation.search.HistoryCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchHistoryCommunicationModule {

    @Binds
    abstract fun mapper(mapper: HistoryCommunication.Base): HistoryCommunication
}