package com.github.skytoph.simpleweather.presentation.favorites

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.presentation.view.shimmer.ShimmerWrapper
import com.google.android.material.tabs.TabLayout

abstract class ShowFavorites {
    fun show(
        errorView: View,
        fragmentManager: FragmentManager,
        contentMenuItem: MenuItem,
        progress: ShimmerWrapper,
        tabLayout: TabLayout,
        requestPermission: () -> Unit,
        submitFavorites: (List<String>) -> Unit,
    ) {
        show(requestPermission)
        show(progress)
        show(fragmentManager)
        show(errorView, contentMenuItem, tabLayout)
        show(submitFavorites)
    }

    open fun show(errorView: View, contentMenuItem: MenuItem, tabLayout: TabLayout) = Unit
    open fun show(fragmentManager: FragmentManager) = Unit
    open fun show(progress: ShimmerWrapper) = Unit
    open fun show(requestPermission: () -> Unit) = Unit
    open fun show(submitFavorites: (List<String>) -> Unit) = Unit
}