package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class ShowStrategy {
    abstract fun show(
        id: String,
        fragment: Fragment,
        @IdRes container: Int,
        fragmentManager: FragmentManager,
    )

    object REPLACE : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            @IdRes container: Int,
            fragmentManager: FragmentManager,
        ) {
            fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit()
        }
    }

    object ADD : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            container: Int,
            fragmentManager: FragmentManager,
        ) {
            fragmentManager.beginTransaction()
                .also { t -> fragmentManager.findFragmentById(container)?.let { t.hide(it) } }
                .add(container, fragment)
                .addToBackStack(id)
                .commit()
        }
    }
}
