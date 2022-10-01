package com.github.skytoph.simpleweather.presentation.favorites

import android.view.View
import androidx.fragment.app.FragmentManager

sealed class FavoritesState {
    abstract fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View)

    object Base : FavoritesState() {
        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            errorView.visibility = View.GONE
            content.forEach { it.visibility = View.VISIBLE }
        }
    }

    class Delete(private val delete: () -> Unit) : FavoritesState() {
        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            DeleteConfirmationDialogFragment
                .newInstance(delete)
                .show(fragmentManager, DeleteConfirmationDialogFragment.TAG)
        }
    }

    object Hidden : FavoritesState() {
        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            errorView.visibility = View.GONE
            content.forEach { it.visibility = View.GONE }
        }
    }

    object Error : FavoritesState() {
        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            errorView.visibility = View.VISIBLE
            content.forEach { it.visibility = View.GONE }
        }
    }
}