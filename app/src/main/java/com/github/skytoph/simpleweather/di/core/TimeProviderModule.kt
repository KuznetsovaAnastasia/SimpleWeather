package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimeProviderModule {

    @Binds
    abstract fun time(time: CurrentTime.Base): CurrentTime
}