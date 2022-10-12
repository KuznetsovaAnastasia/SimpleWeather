package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.search.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchMapperModule {

    @Binds
    abstract fun predictionsDataMapper(mapper: PredictionListToDataMapper.Base): PredictionListToDataMapper

    @Binds
    abstract fun predictionDataMapper(mapper: PredictionToDataMapper.Base): PredictionToDataMapper

    @Binds
    abstract fun dataMapper(mapper: SearchResultsCloudToDataMapper.Base): SearchResultsCloudToDataMapper

    @Binds
    abstract fun dataItemMapper(mapper: SearchItemCloudToDataMapper.Base): SearchItemCloudToDataMapper

    @Binds
    abstract fun domainMapper(mapper: SearchResultsDataToDomainMapper.Base): SearchResultsDataToDomainMapper

    @Binds
    abstract fun domainItemMapper(mapper: SearchItemDataToDomainMapper.Base): SearchItemDataToDomainMapper
}