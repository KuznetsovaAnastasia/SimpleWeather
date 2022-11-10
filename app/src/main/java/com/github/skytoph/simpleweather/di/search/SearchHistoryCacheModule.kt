package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchHistoryCacheModule {

    @Binds
    abstract fun cache(cache: SearchHistoryCache.Base): SearchHistoryCache
}