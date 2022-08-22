package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.location.PlacesDataSource
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SearchDataSourceModule {

    @Binds
    @Singleton
    abstract fun dataSource(dataSource: SearchLocationDataSource.Base): SearchLocationDataSource

    @Binds
    @Singleton
    abstract fun placeDataSource(dataSource: PlacesDataSource.Base): PlacesDataSource
}