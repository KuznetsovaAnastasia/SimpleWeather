package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormat
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeFormatterTest {

    private var locale = Locale.UK
    private val resources = object : LocaleProvider {
        override fun locale(): Locale = locale
    }
    private val format = object : TimeFormat {
        override fun is24HourChosen(): Boolean = true
    }
    private val formatter = TimeFormatter.Base(resources, format)

    @Test
    fun test_duration_formatting() {
        val seconds = 57595L
        val actual = formatter.duration(seconds)

        assertEquals(Pair(15, 59), actual)
    }

    @Test
    fun test_time_formatting_locale_uk() {
        val seconds = 57595L
        locale = Locale.UK
        val actual = formatter.timeFull(seconds)

        assertEquals("15:59", actual)
    }
}