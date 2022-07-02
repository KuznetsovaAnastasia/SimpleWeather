package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen

class MainNavFragment(@IdRes private val container: Int) :
    NavigationScreen("MainNavigationScreen", MainFragment::class.java, container)