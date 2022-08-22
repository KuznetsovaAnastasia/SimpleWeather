package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.search.mapper.*
import com.github.skytoph.simpleweather.domain.search.SearchItemDomainToUiMapper
import com.github.skytoph.simpleweather.domain.search.SearchResultsDomainToUiMapper
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
    abstract fun predictionsDataMapper(mapper: PredictionListToDataMapper.Base): PredictionListToDataMapper

    @Binds
    @Singleton
    abstract fun predictionDataMapper(mapper: PredictionToDataMapper.Base): PredictionToDataMapper

    @Binds
    @Singleton
    abstract fun dataMapper(mapper: SearchResultsCloudToDataMapper.Base): SearchResultsCloudToDataMapper

    @Binds
    @Singleton
    abstract fun dataItemMapper(mapper: SearchItemCloudToDataMapper.Base): SearchItemCloudToDataMapper

    @Binds
    @Singleton
    abstract fun domainMapper(mapper: SearchResultsDataToDomainMapper.Base): SearchResultsDataToDomainMapper

    @Binds
    @Singleton
    abstract fun domainItemMapper(mapper: SearchItemDataToDomainMapper.Base): SearchItemDataToDomainMapper

    @Binds
    @Singleton
    abstract fun uiMapper(mapper: SearchResultsDomainToUiMapper.Base): SearchResultsDomainToUiMapper

    @Binds
    @Singleton
    abstract fun uiItemMapper(mapper: SearchItemDomainToUiMapper.Base): SearchItemDomainToUiMapper
}