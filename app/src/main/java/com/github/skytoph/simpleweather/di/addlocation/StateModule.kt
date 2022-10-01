package com.github.skytoph.simpleweather.di.addlocation

import com.github.skytoph.simpleweather.core.presentation.LiveDataMapper
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingStateMapper
import com.github.skytoph.simpleweather.presentation.addlocation.StateCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object StateModule {

    @Provides
    fun communication(
        communication: LoadingCommunication.Observe,
        stateMapper: LoadingStateMapper,
        mapper: LiveDataMapper,
    ): StateCommunication =
        StateCommunication.Base(communication.map(mapper) { stateMapper.map(it) })
}