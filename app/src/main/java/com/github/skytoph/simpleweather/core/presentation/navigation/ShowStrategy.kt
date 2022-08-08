package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.presentation.BaseFragment

abstract class ShowStrategy {
    abstract fun show(
        id: String,
        fragment: BaseFragment<*, *>,
        @IdRes container: Int,
        fragmentManager: FragmentManager,
    )

    object REPLACE : ShowStrategy() {
        override fun show(
            id: String,
            fragment: BaseFragment<*, *>,
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
            fragment: BaseFragment<*, *>,
            container: Int,
            fragmentManager: FragmentManager,
        ) {
            fragmentManager.beginTransaction()
                .add(container, fragment)
                .also { t -> fragmentManager.findFragmentById(container)?.let { t.hide(it) } }
                .addToBackStack(id)
                .commit()
        }
    }
}
