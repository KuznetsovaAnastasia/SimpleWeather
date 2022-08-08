package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.fragment.app.FragmentManager

class Navigate(
    private val action: NavigationAction,
    private val container: Int = 0,
) : ShowScreen {

    override fun show(fragmentManager: FragmentManager) =
        action.show(fragmentManager, container)
}