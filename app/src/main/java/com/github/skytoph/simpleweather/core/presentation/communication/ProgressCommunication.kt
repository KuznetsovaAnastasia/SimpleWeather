package com.github.skytoph.simpleweather.core.presentation.communication

import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import javax.inject.Inject
import javax.inject.Singleton

interface ProgressCommunication {

    interface Update : Communication.Update<Visibility>

    interface Observe : Communication.Observe<Visibility>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.SingleUiUpdate<Visibility>(), Mutable
}