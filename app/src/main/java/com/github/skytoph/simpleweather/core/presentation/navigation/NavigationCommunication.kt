package com.github.skytoph.simpleweather.core.presentation.navigation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface NavigationCommunication {

    interface Update : Communication.Update<ShowScreen>

    interface Observe : Communication.Observe<ShowScreen>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.SinglePostUpdate<ShowScreen>(), Mutable
}