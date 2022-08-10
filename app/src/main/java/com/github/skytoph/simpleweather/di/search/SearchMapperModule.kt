package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.search.geocode.PredictionCloudToDataMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemDataToUiMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchResultsToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchMapperModule {

    @Binds
    @Singleton
    abstract fun dataMapper(mapper: PredictionCloudToDataMapper.Base): PredictionCloudToDataMapper

    @Binds
    @Singleton
    abstract fun uiMapper(mapper: SearchResultsToUiMapper.Base): SearchResultsToUiMapper

    @Binds
    @Singleton
    abstract fun uiItemMapper(mapper: SearchItemDataToUiMapper.Base): SearchItemDataToUiMapper
}