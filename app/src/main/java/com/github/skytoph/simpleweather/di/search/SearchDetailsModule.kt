package com.github.skytoph.simpleweather.di.search

import com.github.skytoph.simpleweather.domain.search.SearchDetailsInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchDetailsModule {

    @Binds
    @ViewModelScoped
    abstract fun interactor(interactor: SearchDetailsInteractor.Base): SearchDetailsInteractor
}