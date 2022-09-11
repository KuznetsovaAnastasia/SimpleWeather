package com.github.skytoph.simpleweather.di.addlocation

import com.github.skytoph.simpleweather.domain.addlocation.AddLocationInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocationInteractorModule {

    @Binds
    abstract fun interactor(interactor: AddLocationInteractor.Base): AddLocationInteractor
}