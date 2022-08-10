package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun preferenceProvider(provider: PreferencesProvider.Base): PreferencesProvider
}