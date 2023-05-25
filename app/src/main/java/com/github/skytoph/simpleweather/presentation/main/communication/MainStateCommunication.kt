package com.github.skytoph.simpleweather.presentation.main.communication

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface MainStateCommunication : Communication.Mutable<MainState> {

    @ViewModelScoped
    class Base @Inject constructor() : Communication.SingleUiUpdate<MainState>(),
        MainStateCommunication
}