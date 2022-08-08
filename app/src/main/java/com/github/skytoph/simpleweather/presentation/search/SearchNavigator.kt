package com.github.skytoph.simpleweather.presentation.search

interface SearchNavigator {
    fun showPredictionDetails(container: Int, id: String, favorite: Boolean)
}