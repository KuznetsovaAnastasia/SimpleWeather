package com.github.skytoph.simpleweather.core.data

interface SaveItemToCache<T> {
    suspend fun save(source: SaveItem<T>)
}

interface UpdateCachedItem<T> {
    suspend fun update(source: UpdateItem<T>): T
    suspend fun updateLocation(source: UpdateItem<T>): T
}

interface Item<T>: SaveItemToCache<T>, UpdateCachedItem<T>

interface CachedItem<T> : Item<T> {
    fun cache(value: Item<T>)
    fun clear()

    class Empty<T> : CachedItem<T> {
        override suspend fun save(source: SaveItem<T>) {}
        override suspend fun update(source: UpdateItem<T>): T =
            throw IllegalStateException("can not update empty item")

        override suspend fun updateLocation(source: UpdateItem<T>): T =
            throw IllegalStateException("can not update empty item")

        override fun cache(value: Item<T>) {}
        override fun clear() {}
    }
}

abstract class BaseCache<T> : CachedItem<T> {
    private var cached: Item<T> = CachedItem.Empty()

    override fun cache(value: Item<T>) {
        cached = value
    }

    override suspend fun save(source: SaveItem<T>) =
        cached.save(source)

    override suspend fun update(source: UpdateItem<T>): T =
        cached.update(source)

    override suspend fun updateLocation(source: UpdateItem<T>): T =
        cached.updateLocation(source)

    override fun clear() {
        cached = CachedItem.Empty()
    }
}