package com.github.skytoph.simpleweather.presentation.favorites

import com.github.skytoph.simpleweather.core.presentation.communication.Communication

interface FavoritesCommunication : Communication.Mutable<List<String>> {

    class Base : Communication.UiUpdate<List<String>>(), FavoritesCommunication
}
