package com.github.skytoph.simpleweather.core.presentation.error

import android.view.View
import com.google.android.material.snackbar.Snackbar

sealed class UiMessage {
    open fun show(view: View) = Unit

    class ShowSnackbar(private val message: String) : UiMessage() {

        override fun show(view: View) =
            Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).show()
    }
}