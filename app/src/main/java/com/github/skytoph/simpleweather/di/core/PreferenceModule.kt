package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import com.github.skytoph.simpleweather.core.provider.ResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun preferenceProvider(provider: ResourceManager.Base): PreferencesProvider
}