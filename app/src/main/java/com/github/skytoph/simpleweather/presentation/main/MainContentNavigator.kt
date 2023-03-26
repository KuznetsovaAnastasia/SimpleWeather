package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigateBack

interface MainContentNavigator : NavigateBack {
    fun showFavorites(fragmentManager: FragmentManager, @IdRes container: Int)
    fun showSettings(@IdRes container: Int)
    fun showSearch(fragmentManager: FragmentManager, container: Int)
}