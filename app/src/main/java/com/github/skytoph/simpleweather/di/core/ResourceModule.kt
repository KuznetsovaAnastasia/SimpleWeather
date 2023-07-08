package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import com.github.skytoph.simpleweather.core.provider.ResourceManager
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import com.github.skytoph.simpleweather.core.provider.TimeFormatProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ResourceModule {

    @Binds
    abstract fun resourceProvider(provider: ResourceManager.Base): ResourceProvider

    @Binds
    abstract fun resourceManager(provider: ResourceManager.Base): ResourceManager

    @Binds
    abstract fun localeProvider(provider: ResourceManager.Base): LocaleProvider

    @Binds
    abstract fun timeFormatProvider(provider: ResourceManager.Base): TimeFormatProvider
}