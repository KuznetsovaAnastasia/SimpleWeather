package com.github.skytoph.simpleweather.presentation.favorites

import android.view.View
import androidx.fragment.app.FragmentManager

sealed class FavoritesState {
    abstract fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View)

    abstract class Abstract(
        private val contentVisibility: Int,
        private val errorViewVisibility: Int,
    ) : FavoritesState() {

        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            content.forEach { it.visibility = contentVisibility }
            errorView.visibility = errorViewVisibility
        }
    }

    object Base : Abstract(View.VISIBLE, View.GONE)

    object Hidden : Abstract(View.GONE, View.GONE)

    object Error : Abstract(View.GONE, View.VISIBLE)

    class Delete(private val delete: () -> Unit) : FavoritesState() {
        override fun show(errorView: View, fragmentManager: FragmentManager, vararg content: View) {
            DeleteConfirmationDialogFragment
                .newInstance(delete)
                .show(fragmentManager, DeleteConfirmationDialogFragment.TAG)
        }
    }
}