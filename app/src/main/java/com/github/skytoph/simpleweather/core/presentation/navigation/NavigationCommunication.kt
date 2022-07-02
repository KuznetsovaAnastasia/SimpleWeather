package com.github.skytoph.simpleweather.core.presentation.navigation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface NavigationCommunication{

    interface Update: Communication.Update<NavigationScreen>

    interface Observe: Communication.Observe<NavigationScreen>

    interface Mutable: Update, Observe

    class Base: Communication.SinglePostUpdate<NavigationScreen>(), Mutable
}