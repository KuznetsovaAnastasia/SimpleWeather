package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.Matches

abstract class NavigationScreen(
    private val id: String,
    private val fragmentClass: Class<out Fragment>,
    @IdRes private val  container: Int
) : Matches<NavigationScreen>, ShowScreen {

    override fun match(item: NavigationScreen): Boolean = this.id == item.id

    override fun show(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(container, fragmentClass.newInstance())
            .commit()
    }
}