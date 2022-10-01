package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.presentation.favorites.FavoritesStateCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class StateCommunicationModule {

    @Binds
    abstract fun communication(communication: FavoritesStateCommunication.Base): FavoritesStateCommunication
}