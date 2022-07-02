package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen

class SearchNavFragment(@IdRes private val container: Int) :
    NavigationScreen("SearchNavigationFragment", SearchResultFragment::class.java, container)