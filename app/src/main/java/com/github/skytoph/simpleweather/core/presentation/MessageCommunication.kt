package com.github.skytoph.simpleweather.core.presentation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

class MessageCommunication {

    interface Update : Communication.Update<String>

    interface Observe : Communication.Observe<String>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.SingleUiUpdate<String>(), Mutable
}