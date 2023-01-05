package com.github.skytoph.simpleweather.presentation.favorites

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy

class FavoritesNavFragment(@IdRes private val container: Int) :
    NavigationScreen("FavoritesNavigationScreen",
        FavoritesFragment::class.java,
        container,
        ShowStrategy.Add)