package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy


class SearchNavFragment(
    @IdRes private val container: Int,
    strategy: ShowStrategy = ShowStrategy.ADD,
) : NavigationScreen(TAG,
    SearchFragment::class.java,
    container,
    strategy) {

    companion object {
        const val TAG = "SearchNavigationFragment"
    }
}