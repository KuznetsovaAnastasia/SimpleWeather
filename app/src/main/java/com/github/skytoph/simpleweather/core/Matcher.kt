package com.github.skytoph.simpleweather.core

interface Matcher<T> {
    fun matches(item: T): Boolean
    fun contentMatches(item: T): Boolean
}
