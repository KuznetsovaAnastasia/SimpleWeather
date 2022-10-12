package com.github.skytoph.simpleweather.core.presentation.menu

import android.view.MenuItem

class MenuItemExpandListener(private val expand: () -> Unit, private val collapse: () -> Unit) :
    MenuItem.OnActionExpandListener {

    override fun onMenuItemActionExpand(item: MenuItem): Boolean {
        expand.invoke()
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
        collapse.invoke()
        return true
    }
}