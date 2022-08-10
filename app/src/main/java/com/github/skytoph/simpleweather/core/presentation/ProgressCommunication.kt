package com.github.skytoph.simpleweather.core.presentation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface ProgressCommunication {

    interface Update : Communication.Update<Visibility>

    interface Observe : Communication.Observe<Visibility>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<Visibility>(), Mutable
}