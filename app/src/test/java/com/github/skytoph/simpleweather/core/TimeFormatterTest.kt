package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import org.junit.Assert.*
import org.junit.Test

class TimeFormatterTest{

    private val formatter = TimeFormatter.Base()

    @Test
    fun test_duration_formatting(){
        val seconds = 57595L
        val actual = formatter.duration(seconds)

        assertEquals("15H 59M", actual)
    }
}