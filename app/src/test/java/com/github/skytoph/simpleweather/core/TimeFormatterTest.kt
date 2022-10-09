package com.github.skytoph.simpleweather.core

import android.graphics.drawable.Drawable
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormat
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormatterTest {

    private val resources = object : ResourceProvider {
        override fun color(id: Int): Int = 0
        override fun dimensionPixel(id: Int): Int = 0
        override fun string(id: Int): String = "%dH %02dM"
        override fun string(id: Int, vararg args: String) = string(id)
        override fun drawable(id: Int): Drawable? = null
    }
    private val format = object : TimeFormat {
        override fun is24HourChosen(): Boolean = false
    }
    private val formatter = TimeFormatter.Base(resources, format)

    @Test
    fun test_duration_formatting() {
        val seconds = 57595L
        val actual = formatter.duration(seconds)

        assertEquals("15H 59M", actual)
    }
}