package com.github.skytoph.simpleweather.presentation.favorites

import com.github.skytoph.simpleweather.core.presentation.StateMapper
import javax.inject.Inject

class FavoritesStateMapper @Inject constructor() : StateMapper<Boolean, FavoritesState> {

    override fun map(source: Boolean): FavoritesState =
        if (source) FavoritesState.Error else FavoritesState.Base
}