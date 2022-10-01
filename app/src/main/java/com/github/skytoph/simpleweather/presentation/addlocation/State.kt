package com.github.skytoph.simpleweather.presentation.addlocation

import android.view.View
import android.widget.TextView
import com.github.skytoph.simpleweather.core.presentation.view.MessageButton

sealed class State {
    abstract fun show(button: MessageButton, errorView: TextView, content: View)

    object Favorite : State() {
        override fun show(button: MessageButton, errorView: TextView, content: View) {
            button.setClickedStyle()
            button.visibility = View.VISIBLE
        }
    }

    object Initial : State() {
        override fun show(button: MessageButton, errorView: TextView, content: View) {
            button.visibility = View.GONE
            content.visibility = View.VISIBLE
            errorView.visibility = View.GONE
        }
    }

    object Success : State() {
        override fun show(button: MessageButton, errorView: TextView, content: View) {
            button.visibility = View.VISIBLE
            content.visibility = View.VISIBLE
            errorView.visibility = View.INVISIBLE
        }
    }

    object Fail : State() {
        override fun show(button: MessageButton, errorView: TextView, content: View) {
            button.visibility = View.GONE
            content.visibility = View.GONE
            errorView.visibility = View.VISIBLE
        }
    }
}