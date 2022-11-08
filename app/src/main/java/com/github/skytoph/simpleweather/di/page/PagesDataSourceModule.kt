package com.github.skytoph.simpleweather.di.page

import com.github.skytoph.simpleweather.data.pages.PagesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PagesDataSourceModule {

    @Binds
    @Singleton
    abstract fun dataSource(source: PagesDataSource.Base): PagesDataSource
}