package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.Matches
import com.github.skytoph.simpleweather.core.presentation.BaseFragment

abstract class NavigationScreen(
    private val id: String,
    private val fragmentClass: Class<out BaseFragment<*, *>>,
    @IdRes private val container: Int,
    private val strategy: ShowStrategy,
) : Matches<NavigationScreen>, ShowScreen {

    override fun show(fragmentManager: FragmentManager) =
        strategy.show(id, fragment(), container, fragmentManager)

    protected open fun fragment(): BaseFragment<*, *> = fragmentClass.newInstance()

    override fun match(item: NavigationScreen): Boolean = this.id == item.id
}