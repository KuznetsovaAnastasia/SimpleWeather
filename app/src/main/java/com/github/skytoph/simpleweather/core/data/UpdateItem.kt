package com.github.skytoph.simpleweather.core.data

interface UpdateItem<T, I> {
    suspend fun update(data: T): T
}
