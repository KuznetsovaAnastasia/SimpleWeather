package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.fragment.app.FragmentManager

interface NavigationAction {
    fun show(fragmentManager: FragmentManager, container: Int = 0)

    object POPUP : NavigationAction {
        override fun show(fragmentManager: FragmentManager, container: Int) {
            fragmentManager.popBackStack()
        }
    }

    object REMOVE : NavigationAction {
        override fun show(fragmentManager: FragmentManager, container: Int) {
            fragmentManager.findFragmentById(container)?.let {
                fragmentManager.beginTransaction().remove(it).commit()
            }
        }
    }
}