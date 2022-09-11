package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {

    @Binds
    abstract fun handler(errorHandler: ErrorHandler.Ui): ErrorHandler

}