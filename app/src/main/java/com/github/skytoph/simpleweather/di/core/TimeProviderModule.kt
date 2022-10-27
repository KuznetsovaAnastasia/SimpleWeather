package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.data.TimeProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeProviderModule {

    @Binds
    abstract fun time(time: TimeProvider.Base): TimeProvider
}