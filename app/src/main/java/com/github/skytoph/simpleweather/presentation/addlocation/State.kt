package com.github.skytoph.simpleweather.presentation.addlocation

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.github.skytoph.simpleweather.core.presentation.view.MessageButton

sealed class State {
    abstract fun show(button: MessageButton, errorView: TextView, content: View, progressBar: ProgressBar)

    abstract class Abstract(
        private val buttonVisibility: Int,
        private val errorVisibility: Int,
        private val contentVisibility: Int,
        private val progressVisibility: Int,
    ): State(){
        override fun show(
            button: MessageButton,
            errorView: TextView,
            content: View,
            progressBar: ProgressBar
        ) {
            button.visibility = buttonVisibility
            content.visibility = contentVisibility
            errorView.visibility = errorVisibility
            progressBar.visibility = progressVisibility
        }
    }

    object Favorite : Abstract(View.VISIBLE, View.GONE, View.VISIBLE, View.INVISIBLE) {
        override fun show(button: MessageButton, errorView: TextView, content: View, progressBar: ProgressBar) {
            super.show(button, errorView, content, progressBar)
            button.setClickedStyle()
        }
    }

    object Initial : Abstract(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.VISIBLE)

    object Success : Abstract(View.VISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE)

    object Fail : Abstract(View.GONE, View.VISIBLE, View.GONE, View.INVISIBLE)
}