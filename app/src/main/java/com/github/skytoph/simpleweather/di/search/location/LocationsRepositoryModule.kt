package com.github.skytoph.simpleweather.di.search.location

import com.github.skytoph.simpleweather.domain.search.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationsRepositoryModule {

    @Binds
    @Singleton
    abstract fun repository(repository: LocationsRepository.Base): LocationsRepository
}