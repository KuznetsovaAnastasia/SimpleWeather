package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.core.data.RealmProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherCacheModule {

    @Binds
    @Singleton
    abstract fun realmProvider(provider: RealmProvider.Base): RealmProvider
}