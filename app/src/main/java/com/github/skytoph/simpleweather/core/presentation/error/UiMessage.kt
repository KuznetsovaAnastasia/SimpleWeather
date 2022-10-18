package com.github.skytoph.simpleweather.core.presentation.error

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

sealed class UiMessage {
    open fun show(view: View) = Unit

    class ShowSnackbar(@StringRes private val messageId: Int) : UiMessage() {

        override fun show(view: View) =
            Snackbar
                .make(view, view.resources.getString(messageId), Snackbar.LENGTH_INDEFINITE)
                .show()
    }
}