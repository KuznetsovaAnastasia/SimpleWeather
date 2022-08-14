package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.na.NavigateBack

interface MainContentNavigator : NavigateBack {
    fun showFavorites(@IdRes container: Int)
    fun showSearchPredictions(container: Int)
}