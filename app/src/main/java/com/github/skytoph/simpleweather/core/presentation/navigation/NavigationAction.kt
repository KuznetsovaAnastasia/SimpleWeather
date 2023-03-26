package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.fragment.app.FragmentManager

interface NavigationAction {
    fun show(fragmentManager: FragmentManager, tag: String? = null)

    object POPUP : NavigationAction {
        override fun show(fragmentManager: FragmentManager, tag: String?) {
            if (tag.isNullOrEmpty()) fragmentManager.popBackStack()
            else fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    object SetPrimaryFragment : NavigationAction {
        override fun show(fragmentManager: FragmentManager, tag: String?) {
            val primaryFragment = fragmentManager.findFragmentByTag(tag)
            fragmentManager.beginTransaction()
                .setPrimaryNavigationFragment(primaryFragment)
                .commit()
        }
    }
}