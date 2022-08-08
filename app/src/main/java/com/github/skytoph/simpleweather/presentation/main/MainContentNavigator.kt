package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes

interface MainContentNavigator {
    fun showFavorites(@IdRes container: Int)
    fun showSearchPredictions(container: Int)
}