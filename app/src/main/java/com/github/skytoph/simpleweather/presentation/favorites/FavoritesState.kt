package com.github.skytoph.simpleweather.presentation.favorites

import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.github.skytoph.simpleweather.R

sealed class FavoritesState : ShowFavorites() {
    override fun show(
        errorView: View,
        fragmentManager: FragmentManager,
        contentMenuItem: MenuItem,
        progress: ProgressBar,
        vararg content: View,
        requestPermission: () -> Unit,
    ) {
        show(requestPermission)
        show(progress)
        show(fragmentManager)
        show(errorView, contentMenuItem, *content)
    }

    abstract class Abstract(
        private val contentVisibility: Int,
        private val errorViewVisibility: Int,
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
    }

    object Base : Abstract(View.VISIBLE, View.GONE)

    object Hidden : Abstract(View.GONE, View.GONE)

    object Empty : Abstract(View.GONE, View.VISIBLE)

    object RequestPermission : FavoritesState() {
        override fun show(requestPermission: () -> Unit) {
            requestPermission()
        }
    }

    class Progress(private val show: Boolean) : FavoritesState() {
        override fun show(progress: ProgressBar) {
            progress.visibility = if (show) View.VISIBLE else View.INVISIBLE
        }
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