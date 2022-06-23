package com.github.skytoph.simpleweather.core

interface ProbabilityFormatter {
    fun format(number: Double): String

    class Base: ProbabilityFormatter{

        override fun format(number: Double): String = "${(number * 100).toInt()}%"
    }
}