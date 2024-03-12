package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.domain.FunctionHandler
import com.github.skytoph.simpleweather.core.domain.RequestHandler
import com.github.skytoph.simpleweather.domain.weather.WeatherHandler
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface FunctionHandlerModule {

    @Binds
    @ViewModelScoped
    fun requestHandler(handler: RequestHandler): FunctionHandler<Unit>

    @Binds
    @ViewModelScoped
    fun weatherHandler(handler: WeatherHandler): FunctionHandler<WeatherUi>
}