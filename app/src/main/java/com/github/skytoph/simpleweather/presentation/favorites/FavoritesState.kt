package com.github.skytoph.simpleweather.presentation.favorites

import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.view.shimmer.ShimmerWrapper

sealed class FavoritesState : ShowFavorites() {

    abstract class Abstract(
        private val contentVisibility: Int,
        private val errorViewVisibility: Int,
        private val isProgressVisible: Boolean,
    ) : FavoritesState() {

        override fun show(
            errorView: View,
            contentMenuItem: MenuItem,
            vararg content: View,
        ) {
            content.forEach { it.visibility = contentVisibility }
            contentMenuItem.isVisible = contentVisibility == View.VISIBLE
            errorView.visibility = errorViewVisibility
        }

        override fun show(progress: ShimmerWrapper) = progress.show(isProgressVisible)
    }

    object Hidden : Abstract(View.GONE, View.GONE, true)

    object Base : Abstract(View.VISIBLE, View.GONE, false)

    object Empty : Abstract(View.GONE, View.VISIBLE, false)

    object RequestPermission : FavoritesState() {
        override fun show(requestPermission: () -> Unit) {
            requestPermission()
        }
    }

    object Progress : FavoritesState() {
        override fun show(progress: ShimmerWrapper) = progress.show(true)
    }

    abstract class Dialog(
        private val action: () -> Unit,
        @StringRes private val title: Int,
        private val tag: String,
    ) : FavoritesState() {
        override fun show(fragmentManager: FragmentManager) {
            ConfirmationDialogFragment
                .newInstance(action, title)
                .show(fragmentManager, tag)
        }
    }

    class AddCurrentLocation(addCurrentLocation: () -> Unit) :
        Dialog(addCurrentLocation, R.string.add_current_location, "AddCurrentLocationDialog")

    class Delete(delete: () -> Unit) :
        Dialog(delete, R.string.delete_location, "DeleteConfirmationDialog")
}