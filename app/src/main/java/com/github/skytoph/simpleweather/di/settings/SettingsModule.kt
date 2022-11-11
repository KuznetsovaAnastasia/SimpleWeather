package com.github.skytoph.simpleweather.di.settings

import com.github.skytoph.simpleweather.domain.settings.SettingsInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun interactor(interactor: SettingsInteractor.Base): SettingsInteractor
}