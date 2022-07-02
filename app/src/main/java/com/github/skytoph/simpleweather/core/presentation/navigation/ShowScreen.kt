package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ShowScreen {
    fun show(fragmentManager: FragmentManager)
}
