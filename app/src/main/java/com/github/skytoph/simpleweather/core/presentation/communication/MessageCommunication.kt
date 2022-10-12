package com.github.skytoph.simpleweather.core.presentation.communication

import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import javax.inject.Inject
import javax.inject.Singleton

class MessageCommunication {

    interface Update : Communication.Update<UiMessage>

    interface Observe : Communication.Observe<UiMessage>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.SinglePostUpdate<UiMessage>(), Mutable
}