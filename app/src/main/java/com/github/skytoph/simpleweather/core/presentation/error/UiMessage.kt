package com.github.skytoph.simpleweather.core.presentation.error

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

sealed class UiMessage {
    open fun show(view: View) = Unit

    abstract class SnackbarAbstract(
        @StringRes private val messageId: Int,
        private val duration: Int,
    ) : UiMessage() {

        override fun show(view: View) =
            Snackbar.make(view, view.resources.getString(messageId), duration).show()
    }

    class SnackbarIndefinite(@StringRes private val messageId: Int) :
        SnackbarAbstract(messageId, Snackbar.LENGTH_INDEFINITE)

    class SnackbarShort(@StringRes private val messageId: Int) :
        SnackbarAbstract(messageId, Snackbar.LENGTH_SHORT)
}