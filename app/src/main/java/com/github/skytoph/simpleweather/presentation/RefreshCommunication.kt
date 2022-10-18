package com.github.skytoph.simpleweather.presentation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

class RefreshCommunication {

    interface Update : Communication.Update<RefreshData>

    interface Observe : Communication.Observe<RefreshData>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Mutable, Communication.UiUpdate<RefreshData>()
}

enum class RefreshData {
    CACHE,
    LOCATION
}