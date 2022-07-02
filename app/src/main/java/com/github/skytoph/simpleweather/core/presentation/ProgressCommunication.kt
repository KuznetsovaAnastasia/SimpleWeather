package com.github.skytoph.simpleweather.core.presentation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface ProgressCommunication {

    interface Update : Communication.Update<Visibility>

    interface Observe : Communication.Observe<Visibility>

    interface Mutable : Update, Observe

    class Base: Communication.UiUpdate<Visibility>(), Mutable
}