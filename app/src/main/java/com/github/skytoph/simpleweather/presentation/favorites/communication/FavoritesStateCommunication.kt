package com.github.skytoph.simpleweather.presentation.favorites.communication

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesStateCommunication : Communication.MutableEvents<FavoritesState> {

    @ViewModelScoped
    class Base @Inject constructor() : Communication.MultipleEvents<FavoritesState>(),
        FavoritesStateCommunication
}