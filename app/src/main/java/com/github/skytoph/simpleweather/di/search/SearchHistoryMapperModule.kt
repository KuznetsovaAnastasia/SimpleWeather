package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryDataDBMapper
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryDataMapper
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryListDataMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryDomainMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListDomainMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListUiMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchHistoryMapperModule {

    @Binds
    abstract fun dataMapper(mapper: SearchHistoryDataMapper.Base): SearchHistoryDataMapper

    @Binds
    abstract fun dataListMapper(mapper: SearchHistoryListDataMapper.Base): SearchHistoryListDataMapper

    @Binds
    abstract fun dbMapper(mapper: SearchHistoryDataDBMapper.Base): SearchHistoryDataDBMapper

    @Binds
    abstract fun domainMapper(mapper: SearchHistoryDomainMapper.Base): SearchHistoryDomainMapper

    @Binds
    abstract fun domainListMapper(mapper: SearchHistoryListDomainMapper.Base): SearchHistoryListDomainMapper

    @Binds
    abstract fun uiMapper(mapper: SearchHistoryUiMapper.Base): SearchHistoryUiMapper

    @Binds
    abstract fun uiListMapper(mapper: SearchHistoryListUiMapper.Base): SearchHistoryListUiMapper
}