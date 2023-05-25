package com.github.skytoph.simpleweather.di.addlocation

import com.github.skytoph.simpleweather.core.presentation.StateMapper
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingStateMapper
import com.github.skytoph.simpleweather.presentation.addlocation.State
import com.github.skytoph.simpleweather.presentation.addlocation.communication.Loading
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoadingStateMapperModule {

    @Binds
    abstract fun mapper(mapper: LoadingStateMapper.Base): StateMapper<Loading, State>

    @Binds
    abstract fun stateMapper(mapper: LoadingStateMapper.Base): LoadingStateMapper
}