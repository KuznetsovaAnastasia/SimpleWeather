package com.github.skytoph.simpleweather.presentation.favorites

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface FavoritesStateCommunication : Communication.MultipleMutable<FavoritesState> {

    @ViewModelScoped
    class Base @Inject constructor() : Communication.MultipleUiUpdates<FavoritesState>(),
        FavoritesStateCommunication
}