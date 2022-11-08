package com.github.skytoph.simpleweather.di.service

import com.github.skytoph.simpleweather.domain.work.UpdateForecastInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkerModule {

    @Binds
    abstract fun workerInteractor(interactor: UpdateForecastInteractor.Base): UpdateForecastInteractor
}