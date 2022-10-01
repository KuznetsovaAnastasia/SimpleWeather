package com.github.skytoph.simpleweather.presentation.favorites

import android.view.View

sealed class FavoritesState {
    abstract fun show(errorView: View, vararg content: View)

    object Base : FavoritesState() {
        override fun show(errorView: View, vararg content: View) {
            errorView.visibility = View.GONE
            content.forEach { it.visibility = View.VISIBLE }
        }
    }

    object Hidden : FavoritesState() {
        override fun show(errorView: View, vararg content: View) {
            errorView.visibility = View.GONE
            content.forEach { it.visibility = View.GONE }
        }
    }

    object Error : FavoritesState() {
        override fun show(errorView: View, vararg content: View) {
            errorView.visibility = View.VISIBLE
            content.forEach { it.visibility = View.GONE }
        }
    }
}