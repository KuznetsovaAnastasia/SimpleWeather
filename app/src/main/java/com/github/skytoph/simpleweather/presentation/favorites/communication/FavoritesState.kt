package com.github.skytoph.simpleweather.presentation.favorites.communication

import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.view.shimmer.ShimmerWrapper
import com.github.skytoph.simpleweather.presentation.favorites.ConfirmationDialogFragment
import com.github.skytoph.simpleweather.presentation.favorites.ShowFavorites
import com.google.android.material.tabs.TabLayout

sealed class FavoritesState : ShowFavorites() {

    abstract class Abstract(
        private val contentVisibility: Int,
        private val errorViewVisibility: Int,
        private val isProgressVisible: Boolean,
    ) : FavoritesState() {

        override fun show(
            errorView: View,
            contentMenuItem: MenuItem,
            tabLayout: TabLayout,
        ) {
            tabLayout.visibility = contentVisibility
            contentMenuItem.isVisible = contentVisibility == View.VISIBLE
            errorView.visibility = errorViewVisibility
        }

        override fun show(progress: ShimmerWrapper) = progress.show(isProgressVisible)
    }

    class Base(private val favorites: List<String>) : Abstract(View.VISIBLE, View.GONE, false) {
        override fun show(submitFavorites: (List<String>) -> Unit) {
            submitFavorites(favorites)
        }
    }

    object Empty : Abstract(View.GONE, View.VISIBLE, false) {
        override fun show(submitFavorites: (List<String>) -> Unit) {
            submitFavorites(emptyList())
        }
    }

    object RequestPermission : FavoritesState() {
        override fun show(requestPermission: () -> Unit) {
            requestPermission()
        }
    }

    class Progress(private val visible: Boolean = false) : FavoritesState() {
        override fun show(progress: ShimmerWrapper) = progress.show(visible)
    }

    abstract class Dialog(
        private val action: () -> Unit,
        private val cancel: () -> Unit,
        @StringRes private val title: Int,
        private val tag: String,
    ) : FavoritesState() {
        override fun show(fragmentManager: FragmentManager) {
            ConfirmationDialogFragment.newInstance(action, cancel, title)
                .show(fragmentManager, tag)
        }
    }

    class AddCurrentLocation(addCurrentLocation: () -> Unit, cancel: () -> Unit) :
        Dialog(
            addCurrentLocation,
            cancel,
            R.string.add_current_location,
            "AddCurrentLocationDialog"
        )

    class Delete(delete: () -> Unit) :
        Dialog(delete, {}, R.string.delete_location, "DeleteConfirmationDialog")
}