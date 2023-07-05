package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.core.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    @Singleton
    fun logger(): Logger = if (BuildConfig.DEBUG) Logger.Debug() else Logger.Remote()
}