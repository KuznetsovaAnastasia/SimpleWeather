package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import com.github.skytoph.simpleweather.core.util.formatter.FormatPatterns
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormat
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeFormatterTest {

    private var locale = Locale.UK
    private var format24HoursChosen = true
    private val resources = object : LocaleProvider {
        override fun locale(): Locale = locale
    }
    private val format = object : TimeFormat {
        override fun is24HourChosen(): Boolean = format24HoursChosen
    }
    private val patterns = FormatPatterns.Base(format)
    private val formatter = TimeFormatter.Base(resources, patterns)

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

    @Test
    fun test_date_time_formatting() {
        val seconds = 57595L
        locale = Locale.UK
        val actual = formatter.dateAndTime(seconds)

        assertEquals("01.01, 15:59", actual)
    }
}