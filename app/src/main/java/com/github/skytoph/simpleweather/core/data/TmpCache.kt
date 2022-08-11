package com.github.skytoph.simpleweather.core.data

interface SaveItemToCache<T> {
    suspend fun save(source: SaveItem<T>)
}

interface CachedItem<T> : SaveItemToCache<T> {
    fun cache(value: SaveItemToCache<T>)
    fun clear()

    class Empty<T> : CachedItem<T> {
        override suspend fun save(source: SaveItem<T>) {}
        override fun cache(value: SaveItemToCache<T>) {}
        override fun clear() {}
    }
}

abstract class BaseCache<T> : CachedItem<T> {
    private var cached: SaveItemToCache<T> = CachedItem.Empty()

    override fun cache(value: SaveItemToCache<T>) {
        cached = value
    }

    override suspend fun save(source: SaveItem<T>) = cached.save(source)

    override fun clear() {
        cached = CachedItem.Empty()
    }
}