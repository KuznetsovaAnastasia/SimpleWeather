package com.github.skytoph.simpleweather.core.presentation.view.visibility

import android.view.View

interface Visibility {

    fun apply(vararg views: View)

    abstract class Abstract(private val visibility: Int) : Visibility {

        override fun apply(vararg views: View) = views.forEach { view ->
            view.visibility = visibility
        }
    }

    class Visible : Abstract(View.VISIBLE)
    class Invisible : Abstract(View.INVISIBLE)
    class Gone : Abstract(View.GONE)
}
