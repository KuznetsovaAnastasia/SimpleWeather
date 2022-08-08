package com.github.skytoph.simpleweather.core.presentation.navigation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface NavigationCommunication {

    interface Update : Communication.Update<ShowScreen>

    interface Observe : Communication.Observe<ShowScreen>

    interface Mutable : Update, Observe

    class Base : Communication.SinglePostUpdate<ShowScreen>(), Mutable
}