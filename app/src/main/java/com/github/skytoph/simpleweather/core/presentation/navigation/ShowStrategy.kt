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

    object Add : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            @IdRes container: Int,
            fragmentManager: FragmentManager,
        ) {
            fragmentManager.beginTransaction()
                .add(container, fragment)
                .commit()
        }
    }

    object Replace : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            container: Int,
            fragmentManager: FragmentManager,
        ) {
            fragmentManager.beginTransaction()
                .replace(container, fragment)
                .addToBackStack(id)
                .commit()
        }
    }
}
