package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.presentation.favorites.FavoritesCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesCommunicationModule {

    @Binds
    abstract fun communication(communication: FavoritesCommunication.Base): FavoritesCommunication
}