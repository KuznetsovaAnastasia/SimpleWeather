package com.github.skytoph.simpleweather.presentation.favorites

import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.core.presentation.view.shimmer.ShimmerWrapper

abstract class ShowFavorites {
    fun show(
        errorView: View,
        fragmentManager: FragmentManager,
        contentMenuItem: MenuItem,
        progress: ShimmerWrapper,
        vararg content: View,
        requestPermission: () -> Unit,
    ) {
        show(requestPermission)
        show(progress)
        show(fragmentManager)
        show(errorView, contentMenuItem, *content)
    }

    open fun show(errorView: View, contentMenuItem: MenuItem, vararg content: View) = Unit
    open fun show(fragmentManager: FragmentManager) = Unit
    open fun show(progress: ShimmerWrapper) = Unit
    open fun show(requestPermission: () -> Unit) = Unit
}