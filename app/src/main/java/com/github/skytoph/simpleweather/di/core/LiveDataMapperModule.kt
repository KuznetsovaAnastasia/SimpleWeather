package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.presentation.LiveDataMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LiveDataMapperModule {

    @Binds
    abstract fun mapper(mapper: LiveDataMapper.Base): LiveDataMapper
}