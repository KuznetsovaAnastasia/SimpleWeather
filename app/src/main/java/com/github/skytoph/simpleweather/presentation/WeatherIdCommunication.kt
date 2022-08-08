package com.github.skytoph.simpleweather.presentation

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface WeatherIdCommunication {

    interface Update : Communication.Update<String>

    interface Observe : Communication.Observe<String>

    interface Mutable : Update, Observe

    class Base : Communication.UiUpdate<String>(), Mutable

}