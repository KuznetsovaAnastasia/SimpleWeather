package com.github.skytoph.simpleweather.presentation.search.nav

import androidx.fragment.app.FragmentManager

interface SearchNavigator {
    fun showSearchDetails(
        fragmentManager: FragmentManager,
        container: Int,
        id: String,
        favorite: Boolean
    )
}