package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.domain.search.SearchInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchInteractorModule {

    @Binds
    abstract fun interactor(interactor: SearchInteractor.Base): SearchInteractor
}