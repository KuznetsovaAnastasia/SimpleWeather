package com.github.skytoph.simpleweather.presentation.favorites.communication

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface FavoritesCommunication : Communication.Mutable<List<String>> {

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<List<String>>(),
        FavoritesCommunication
}
