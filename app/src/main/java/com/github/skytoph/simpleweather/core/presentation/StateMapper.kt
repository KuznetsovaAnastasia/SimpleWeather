package com.github.skytoph.simpleweather.core.presentation

interface StateMapper<S, R> {
    fun map(source: S): R
}