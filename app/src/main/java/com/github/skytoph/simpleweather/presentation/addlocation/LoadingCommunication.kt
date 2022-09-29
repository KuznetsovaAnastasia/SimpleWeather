package com.github.skytoph.simpleweather.presentation.addlocation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface LoadingCommunication {

    interface Update : Communication.Update<Loading>

    interface Observe : Communication.Observe<Loading>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<Loading>(), Mutable
}

enum class Loading {
    INITIAL,
    IN_PROGRESS,
    SUCCESS,
    FAIL
}