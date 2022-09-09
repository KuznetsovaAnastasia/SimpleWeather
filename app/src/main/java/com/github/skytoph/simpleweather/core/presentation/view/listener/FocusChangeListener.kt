package com.github.skytoph.simpleweather.core.presentation.view.listener

import android.view.View

class FocusChangeListener(
    private val focused: () -> Unit = {},
    private val notFocused: () -> Unit = {},
) : View.OnFocusChangeListener {
    override fun onFocusChange(v: View?, hasFocus: Boolean) =
        if (hasFocus) focused.invoke() else notFocused.invoke()
}