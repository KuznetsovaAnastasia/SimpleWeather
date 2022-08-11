package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {

    @Binds
    abstract fun resourceProvider(provider: ResourceProvider.Base): ResourceProvider
}