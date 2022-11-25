package com.github.skytoph.simpleweather.core.data

interface UpdateItemTime<T, I> {
    suspend fun updateTime(data: T, id: I)
}