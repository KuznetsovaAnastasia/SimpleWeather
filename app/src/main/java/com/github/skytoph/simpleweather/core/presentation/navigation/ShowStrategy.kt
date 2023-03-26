package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.presentation.main.MainNavFragment

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
                .replace(container, fragment, id)
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
                .replace(container, fragment, id)
                .addToBackStack(id)
                .commit()
        }
    }

    class AddChild(private val childFragmentManager: FragmentManager) : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            @IdRes container: Int,
            fragmentManager: FragmentManager,
        ) {
            childFragmentManager.beginTransaction()
                .replace(container, fragment, id)
                .commit()
        }
    }

    class ReplaceChild(
        private val childFragmentManager: FragmentManager,
    ) : ShowStrategy() {
        override fun show(
            id: String,
            fragment: Fragment,
            @IdRes container: Int,
            fragmentManager: FragmentManager,
        ) {
            childFragmentManager.beginTransaction()
                .replace(container, fragment, id)
                .addToBackStack(id)
                .commit()
        }
    }
}
