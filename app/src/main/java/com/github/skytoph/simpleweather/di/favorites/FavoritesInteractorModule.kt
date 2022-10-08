package com.github.skytoph.simpleweather.di.favorites

import com.github.skytoph.simpleweather.core.presentation.StateMapper
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesState
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesStateMapper
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

@Module
@InstallIn(ViewModelComponent::class)
abstract class FavoritesStateMapperModule {

    @Binds
    abstract fun mapper(mapper: FavoritesStateMapper): StateMapper<Boolean, FavoritesState>
}
