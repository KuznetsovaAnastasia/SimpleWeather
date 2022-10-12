package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.domain.search.SearchItemDomainToUiMapper
import com.github.skytoph.simpleweather.domain.search.SearchResultsDomainToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchMapperUiModule {

    @Binds
    abstract fun uiMapper(mapper: SearchResultsDomainToUiMapper.Base): SearchResultsDomainToUiMapper

    @Binds
    abstract fun uiItemMapper(mapper: SearchItemDomainToUiMapper.Base): SearchItemDomainToUiMapper
}