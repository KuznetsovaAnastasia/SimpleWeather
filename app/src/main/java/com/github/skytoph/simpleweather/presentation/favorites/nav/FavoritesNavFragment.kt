package com.github.skytoph.simpleweather.presentation.favorites.nav

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesFragment

class FavoritesNavFragment(
    @IdRes private val container: Int,
    strategy: ShowStrategy=ShowStrategy.Add
) : NavigationScreen(
    TAG,
    FavoritesFragment::class.java,
    container,
    strategy
) {

    companion object {
        const val TAG = "FavoritesNavigationScreen"
    }
}