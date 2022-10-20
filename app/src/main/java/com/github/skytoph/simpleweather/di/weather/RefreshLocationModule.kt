package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.domain.weather.RefreshLocation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RefreshLocationModule {

    @Binds
    abstract fun refreshLocation(refresh: RefreshLocation.Base): RefreshLocation

    @Binds
    abstract fun refreshLocationMutable(refresh: RefreshLocation.Base): RefreshLocation.Mutable

    @Binds
    abstract fun locationRefreshed(refresh: RefreshLocation.Base): RefreshLocation.SaveRefreshed
}