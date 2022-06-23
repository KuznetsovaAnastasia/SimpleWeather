package com.github.skytoph.simpleweather.core

import org.junit.Assert.*
import org.junit.Test

class TimeFormatterTest{

    private val formatter = TimeFormatter.Base()

    @Test
    fun test_duration_formatting(){
        val seconds = 57595L
        val actual = formatter.formatDuration(seconds)

        assertEquals("15H 59M", actual)
    }
}