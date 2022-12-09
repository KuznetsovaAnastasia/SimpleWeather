package com.github.skytoph.simpleweather.presentation.favorites

import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager

abstract class ShowFavorites {
    abstract fun show(
        errorView: View,
        fragmentManager: FragmentManager,
        contentMenuItem: MenuItem,
        progress: ProgressBar,
        vararg content: View,
        requestPermission: () -> Unit,
    )

    open fun show(
        errorView: View,
        contentMenuItem: MenuItem,
        vararg content: View,
    ) = Unit

    open fun show(fragmentManager: FragmentManager) = Unit
    open fun show(progress: ProgressBar) = Unit
    open fun show(requestPermission: () -> Unit) = Unit
}