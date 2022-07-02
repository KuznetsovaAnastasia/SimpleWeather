package com.github.skytoph.simpleweather.core

interface Matches<T> {
    fun match(item: T): Boolean
}
