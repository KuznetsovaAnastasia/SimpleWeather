package com.github.skytoph.simpleweather.presentation.main.communication

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.presentation.main.nav.MainContentNavigator

abstract class MainState(protected val navigation: MainContentNavigator) {
    abstract fun show(fragmentManager: FragmentManager, @IdRes container: Int)

    class Search(navigation: MainContentNavigator) : MainState(navigation) {
        override fun show(fragmentManager: FragmentManager, container: Int) =
            navigation.showSearch(fragmentManager, container)
    }

    class Favorites(navigation: MainContentNavigator) : MainState(navigation) {
        override fun show(fragmentManager: FragmentManager, container: Int) =
            navigation.showFavorites(fragmentManager, container)
    }

    class Settings(navigation: MainContentNavigator, @IdRes private val settingsContainer: Int) :
        MainState(navigation) {
        override fun show(fragmentManager: FragmentManager, container: Int) =
            navigation.showSettings(settingsContainer)
    }
}