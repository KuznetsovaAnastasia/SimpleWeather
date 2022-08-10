package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.data.favorites.FavoritesPrefCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoritesDataSourceModule {

    @Binds
    @Singleton
    abstract fun dataSource(source: FavoritesPrefCache.Base): FavoritesPrefCache
}