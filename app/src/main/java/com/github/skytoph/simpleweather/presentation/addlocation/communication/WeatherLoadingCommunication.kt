package com.github.skytoph.simpleweather.presentation.addlocation.communication

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingState
import javax.inject.Inject
import javax.inject.Singleton

interface WeatherLoadingCommunication {

    interface Update : Communication.Update<LoadingState>

    interface Observe : Communication.Observe<LoadingState>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.SingleUiUpdate<LoadingState>(), Mutable
}