package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoritesInteractorModule {

    @Binds
    abstract fun interactor(interactor: FavoritesInteractor.Base): FavoritesInteractor
}