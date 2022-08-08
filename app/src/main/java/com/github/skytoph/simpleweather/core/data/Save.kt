package com.github.skytoph.simpleweather.core.data

interface Save<T> {
    fun save(data: T)
}