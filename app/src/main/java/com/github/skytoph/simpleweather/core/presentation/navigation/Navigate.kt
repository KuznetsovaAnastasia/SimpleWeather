package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.fragment.app.FragmentManager

class Navigate(
    private val action: NavigationAction,
    private val tag: String = "",
) : ShowScreen {

    override fun show(fragmentManager: FragmentManager) =
        action.show(fragmentManager, tag)
}