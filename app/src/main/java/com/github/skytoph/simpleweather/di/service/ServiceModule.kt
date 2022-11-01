package com.github.skytoph.simpleweather.di.service

import com.github.skytoph.simpleweather.domain.service.update.ServiceInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
abstract class ServiceModule {

    @Binds
    @ServiceScoped
    abstract fun serviceInteractor(interactor: ServiceInteractor.Base): ServiceInteractor
}