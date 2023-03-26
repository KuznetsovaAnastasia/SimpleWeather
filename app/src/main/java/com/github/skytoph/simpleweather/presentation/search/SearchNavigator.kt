package com.github.skytoph.simpleweather.presentation.search

import androidx.fragment.app.FragmentManager

interface SearchNavigator {
    fun showSearchDetails(
        fragmentManager: FragmentManager,
        container: Int,
        id: String,
        favorite: Boolean
    )
}