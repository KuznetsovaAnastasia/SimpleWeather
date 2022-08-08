package com.github.skytoph.simpleweather.core.data

interface SaveItem<T> {
    fun saveOrUpdate(id: String, data: T)
}
