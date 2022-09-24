package com.github.skytoph.simpleweather.core.presentation.communication

import javax.inject.Inject
import javax.inject.Singleton

interface ProgressCommunication {

    interface Update : Communication.Update<Boolean>

    interface Observe : Communication.Observe<Boolean>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<Boolean>(), Mutable
}